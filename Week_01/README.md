### Week01

### 0 前置

#### 五遍刷题

1. 第一次写，无想法背
2. 默写，DEBUG，多种写法AC&分析时间复杂度
3. 一天后再写
4. 一周后再写
5. 面试前(具体问题具体分析，明确要求如：元素重复，边界，新空间等)

### 1 数组&链表

#### Array

##### 特点

连续内存空间存放元素

+ 支持随机查找，O(1)
+ 删除，插入，O(n)

##### 套路

+ 双循环&三循环遍历数组

	将数组问题分解到遍历循环中解决。

	```java
	public void doubleLoop(int[] a){
	    for (int i = 0; i < a.length - 1; i++) {
	        System.out.print(a[i] + " ");
	        for (int j = i+1; j < a.length; j++) {
	            System.out.print(a[j] + " ");
	        }
	        break;
	    }
	}
	public void tribeLoop(int[] a){
	    for (int i = 0; i < a.length - 2; i++) {
	        System.out.print(a[i] + " ");
	        for (int j = i+1; j < a.length-1; j++) {
	            System.out.print(a[j] + " ");
	            for (int k = j+1; k < a.length; k++) {
	                System.out.print(a[k] + " ");
	            }
	            break;
	        }
	        break;
	    }
	}
	```

+ 双指针夹逼

	在数组左右标记指针`j&j`，通过指针在指定条件下的移动实现求解。

+ 指针 `i&j` 变换

	结合数组遍历或指针夹逼，符合题解条件时进行值交换实现求解。

+ 空间换时间&升维

#### Linked List

##### 特点

保存下个元素指针，元素不连续

+ 查找需遍历，O(n)
+ 删除，插入，O(1)
+ 单相链表 `node.next = nextNode`
+ 双向链表 `node.pre = preNode;node.next = nextNode`
+ 循环链表 `head.next = nextNode;tail.next = head`

##### 套路

解法固定

[reverse-linked-list](https://leetcode.com/problems/reverse-linked-list/)

[reverse-linked-list-ii](https://leetcode.com/problems/reverse-linked-list-ii/)

[linked-list-cycle](https://leetcode.com/problems/linked-list-cycle/)

[linked-list-cycle-ii](https://leetcode.com/problems/linked-list-cycle-ii/)

[swap-nodes-in-pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)

[reverse-nodes-in-k-group](https://leetcode.com/problems/reverse-nodes-in-k-group/)

#### Skip List

#### 特点

有序的链表，需要抽出一层层索引提高查找效率。

需要维护索引，增加删除元素导致索引距离不平衡。

##### 时间复杂度

![SkipList 索引结构](https://images-1252818907.cos.ap-chengdu.myqcloud.com/images/skiplist.jpg)

>每两个结点抽出一个结点作为上一级索引的结点，第一级索引的结点个数大约就是 n/2，第二级索引的结点个数大约就是 n/4，第三级索引的结点个数大约就是 n/8，依次类推，即第 k 级索引的结点个数是第 k-1 级索引的结点个数的 1/2，那第 k级索引结点的个数就是 n/(2k)。
>
>假设索引有 h 级，最高级的索引有 2 个结点。通过上面的公式，可得 n/(2h)=2，从而求得 h=log2n-1。如果包含原始链表这一层，整个跳表的高度就是 log2n。
>
>跳表中查询某个数据的时候，如果每一层都要遍历 m 个结点，那在跳表中查询一个数据的时间复杂度就是 O(m*logn)，即O(logn)。
>
>总结：查找O(logn) 插入O(logn) 删除O)(logn)

#### 工程应用

Redis底层数据结构-SkipList

Redis中插入、删除、查找以及迭代输出有序序列这几个操作，红黑树也可以完成，时间复杂度跟跳表一样。

但按照区间来查找数据这个操作，红黑树效率没有跳表高。

对于按照区间查找数据这个操作，跳表可以做到 O(logn) 的时间复杂度定位区间的起点，然后在原始链表中顺序往后遍历即可。这样做非常高效。

### 2 队列&栈

#### Queue

+ FIFO
+ En/Dequeue O(1) 
+ Search O(n)

#### Stack

##### 特点

+ LIFO
+ Pop Push O(1)
+ Search O(n)

##### 套路

两个队列实现栈

#### Deque

双端队列

### Queue和Priority Queue分析

首先看Queue，作为一个接口Queue提供了对队列基本的操作方法。

```java
public interface Queue<E> extends Collection<E> {
    /**
     * 入队
     * add与offer的区别在于add元素时，当超过最大容量会抛出异常
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     */
    boolean add(E e);
    boolean offer(E e);

    /**
     * 出队 
     * remove与poll的区别在于，remove元素时，若元素不存在会抛出异常
     */
    E remove();
    E poll();

    /**
     * 查看对头元素
     * element与peek的区别在于，element时，若队列为空会报出异常
     */
    E element();
    E peek();
}
```

Priority Queue

```java
// 只复制了关键字段和方法
public class PriorityQueue<E> extends AbstractQueue<E>
    implements java.io.Serializable {
	// 默认队列长度是11
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
	// 记录队列元素数量
    private int size = 0;
    // 实现元素定制排序
    private final Comparator<? super E> comparator;
    //部分虚拟机会在数组头保留一些数据，因此尝试过多开辟数组空间可能会导致OOM
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
   
    // 该队列通过一个平衡二叉排序堆维护优先级 小顶堆
    // 树的子节点是 queue[2*n+1] queue[2*(n+1)]
    transient Object[] queue; // non-private to simplify nested class access

	// 构造器在初始化对象时就初始化了底层数组
    public PriorityQueue(int initialCapacity,
                         Comparator<? super E> comparator) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    // 扩容
    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // 如果当前容量<64，则基于最新的最小容量*2扩容 否则基于当前容量扩容1.5
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                         (oldCapacity + 2) :
                                         (oldCapacity >> 1));
        // 元素数量过大时的处理
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        //将旧元素复制一个新扩容的数组中
        queue = Arrays.copyOf(queue, newCapacity);
    }

	// 入队
    public boolean offer(E e) {
        if (e == null) // 数据检查
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length) // 扩容检查
            grow(i + 1); // 扩容
        size = i + 1;
        if (i == 0) //说明当前入队的是第一个元素，可以直接入队
            queue[0] = e;
        else   // 关键方法 实现有序入队
            siftUp(i, e);
        return true;
    }

	// 删除队列指定元素(通过equals()方法)
    // 若有多个相同元素，只移除第一个遍历到的元素
    public boolean remove(Object o) {
        int i = indexOf(o); //遍历通过equals()方法找到元素位置
        if (i == -1)
            return false;
        else {
            removeAt(i);
            return true;
        }
    }
	
	// 出队
    @SuppressWarnings("unchecked")
    public E poll() {
        if (size == 0)
            return null;
        int s = --size;
        modCount++;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x); 
        return result;
    }

    // 入堆 将元素X插入数组的K位置上，通过比较提升子节点X在堆中的位置来维护更新堆中元素的顺序
    // 虽然排序实现可能不同，但内部插入元素方式一样 因此只分析一个方法
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x); // 定制排序 
        else
            siftUpComparable(k, x); // 自然排序
    }
    
    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
                break; // 若插入元素比父节点大，跳出循环 直接放入当前位置
            queue[k] = e; // 若插入元素比父节点小 父节点元素成为子节点 当前元素继续和上层的父节点比较
            k = parent;
        }
        queue[k] = x;
    }

	// 入堆 将元素X插入数组的K位置上
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right]; // 比较左右子节点，小的放左边
            if (key.compareTo((E) c) <= 0) // 元素比当前节点小就跳出循环
                break;
            queue[k] = c; // 元素比当前节点大 继续向下寻找
            k = child;
        }
        queue[k] = key;
    }

   // 堆化
    @SuppressWarnings("unchecked")
    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) queue[i]);
    }
}
```

