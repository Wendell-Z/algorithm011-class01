学习笔记

### Week02

### HashMap

+ `put（）`时初始化数组

+ 默认长度`16`

+ 默认加载因子`0.75`，要求大于0

+ 指定了长度时，临界值为大于该长度的最小的2次幂，`put()`时初始化一个临界值长度的数组

	如指定长度7，实际会初始化一个为长度8的数组，临界值为6


### JDK7源码

+ `hash(key)`：通过对象的`hashCode()`方法和一定算法计算，减少冲突

+ `indexFor(hash,table.length)`：计算角标， `hash & length-1`

+ 从角标出第一个链表元素开始比较，通过hash,==和equals进行比较

	**角标同不一定hash就同**

	hash计算出角标，为空，直接放

	不空，比较hash，hash不同，头插法放入元素

	hash同，key进行==比较，相同，替换旧值

	==不同，equals比较，相同替换旧值

	equals不同，头插法放入元素

+ `modCount++`：**代码不合理，还未放入元素就进行了++**

+ `addEntry(hash,key,value,index)`：

	元素数>=临界值且角标上元素不为空才扩容；

	扩容`resize()`扩数组长度两倍，扩好后根据新数组长度重新hash出角标

	接着调用`createEntry()`添加头插法添加元素

+ **createEntry()出现了数据丢失隐患**

```java
void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> e = table[bucketIndex]; // e -> oldValue
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        size++;
    }
```

两线程同时执行到line2，都取到了当前角标的第一个元素，称为`oldValue`。

线程A执行line3，新链表为`newValueA`指向`oldValue`；

线程B执行line3，新链表为`newValueB`指向`oldValue`；

造成了`newValueA`丢失。

+ **resize()出现了数据丢失隐患**

```java
    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }
    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        for (Entry<K,V> e : table) {
            while(null != e) {
                Entry<K,V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }
```

1. line10是线程共享变量，多线程执行，扩容完会造成数据被覆盖丢失

2. A线程执行line9时，通过遍历`table`采用头插法放置到`newTable`中，若此时B线程执行`put()`，可能会出现A线程已遍历过B线程`put()`的位置，造成B线程添加元素丢失。

3. 多线程同时执行`transfer()`方法时，导致next提前被置为null。

	线程A执行到line23时，线程B执行出现数据丢失

	**随手草稿，很烂请忽略，之后再整理。**

![](https://images-1252818907.cos.ap-chengdu.myqcloud.com/images/handwrite.png)

### JDK8源码

相对于JDK7的改进

`Entry`改为`Node`

出现链表替换红黑树 由O(N)提高到O(logn) 8 64 6

尾插法解决了死链问题

![](https://images-1252818907.cos.ap-chengdu.myqcloud.com/images/死链.jpg)

### ConcurrentHashMap

8

64

6

`volatile table[]`数组

`volatile nextTable[]`扩容时的新数组，比旧数组大两倍

`volatile Node`普通数据节点,实现Map.Entry接口

`static final calss Node`子类Treebin保存了对红黑树根节点的引用，维护桶内树读写锁

`static final calss Node`子类TreeNode红黑树数据节点

`static final calss Node`子类ForwardingNode，在槽上放置此节点，对旧table上的操作会转发到nextTable上

`static final calss Node`子类ReservationNode,通过占位进行加锁，比如执行computeIfAbsent()时

`volatile int sizeCtl`   -1 正在初始化中 -n n-1个线程正在扩容中 >table初始化或扩容的大小 0 默认初始化和扩容大小

#### JDK7

使用了`Segment`的分段锁技术，类似于Redis中的哈希槽分布，每个`Segment`管一部分Entry。

put时先通过hash找到对应的Segment，进入到对应的Segment中取put数据，因此这种情况，真正地put也要加锁

获取锁时先自旋获取锁，达到重试次数后改为阻塞获取锁，保证锁能获取成功

**这样理想情况下可以支持Segment[].length个线程同时访问成功**

get时因为value通过volatile修饰，所以保证取到新值

#### JDK8

**LoadeFactor：不指定默认0.75 **

**指定的loaderFactor值只用来计算初始化容量**

**临界值永远是length * 0.75**

**到达size时才扩容**

采用`CAS+sychronized`保证并发

#### put：

计算哈希角标

判断初始化数组

若数组对应角标为空，直接CAS放元素

若当前角标节点为`forwardingNode`，协助转移，转移完再循环放值

`sychronized`锁住槽代码进行对put，其中相同就替换旧值，不同就尾插法放入新值或红黑树

判断链表数是否>8，大于就转红黑树，满足条件的话

不大于就`addCount()`中计算是否扩容

返回`oldValue`或`null`

#### 扩容：

空 结束

if>64 小于扩容

大于的话 看链表 换不换树

红黑树同`HashMap`

借助CAS等手段优化了size()方法

#### 优化size()

JDK7 JDK8的`size()`计算都不是100%准确

JDK7中计算方式：

+ 遍历`Segment`中的所有`size`和`modcount`
+ 遍历完后若`segment`无更改 返回`size`总和
+ 遍历完后若有更改，且遍历次数<=2，重复第一步
+ 遍历次数>2，所有`Segment`加锁，这样大大影响效率

JDK8中计算方式：

+ 两个属性 `int beasCount`  `CounterCell[] counterCells`
+ 并发量小直接CAS `basecount`
+ 更新`basecount`冲突，启用`coutnerCell`，存到不同的`counterCell`中
+ `counterCell`冲突 扩容`CouterCell`
+ 扩容时会去尝试更新`basecount`
+ 最后返回`basecount+`和`counterCell`中的值