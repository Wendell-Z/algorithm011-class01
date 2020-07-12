学习笔记

### Week 03

### 递归模板

```java
// Java
public void recur(int level, int param) { 
  // terminator 递归的终结条件
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 本层具体逻辑
  process(level, param); 
  // drill down 继续递归
  recur( level: level + 1, newParam); 
  // restore current status 处理本层的数据
 
}
```

### 分治模板

```python
# Python
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 递归终结条件
  if problem is None: 
	print_result 
	return 
  # prepare data 根据问题抽象出数据
  data = prepare_data(problem) 
  # 处理子问题
  subproblems = split_problem(problem, data) 
  # conquer subproblems 得到每个子问题的结果
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …
  # process and generate the final result 合并处理最终结果 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states 处理本层数据状态
```

### 归并排序示例递归分治

#### 基本思路

归并排序(二路归并排序)
利用递归思和分治想，将原序列从中间分割成两个子序列，
对分割好的子序列进行重复操作，
最后将拆分好的子序列进行排序，形成新的子序列，然后对子序列进行合并
形成排好序的新序列。

#### 动图示例

![Merge Sort](https://user-gold-cdn.xitu.io/2018/7/18/164acc1ed39a391e?imageslim)

#### 算法复杂度分析

| 平均     | 最坏     | 最好     | 稳定性 | 空间复杂度 |
| :------- | :------- | :------- | :----- | :--------- |
| O(nlogn) | O(nlogn) | O(nlogn) | 稳定   | O(n+logn)  |

p.s.
n个元素都要被遍历一遍以保证其被放到newArray,
需要将待排序序列中的所有记录扫描一遍，所以O(n)。
由完全二叉树可知，整个归并排序需要 log2n次，
所以 最好=最坏=平均=O(n*logn)。
由于归并排序需要与原始记录序列同样数量的存储空间存放归并结果以及递归时log2n的栈空间，
所以空间复杂度O(n+logn)。

#### 代码实现

```java
import java.util.Arrays;
import java.util.Random;

/**
 * MergingSort
 * 归并排序(二路归并排序)
 * 利用递归思和分治想，将原序列从中间分割成两个子序列，
 * 对分割好的子序列进行重复操作，
 * 最后将拆分好的子序列进行排序，形成新的子序列，然后对子序列进行合并
 * 形成排好序的新序列
 * 时间复杂度分析
 * n个元素都要被遍历一遍以保证其被放到newArray,
 * 需要将待排序序列中的所有记录扫描一遍，O(n)
 * 由完全二叉树可知，整个归并排序需要 log2n次
 * 所以 最好=最坏=平均=O(n*logn)
 * 由于归并排序需要与原始记录序列同样数量的存储空间存放归并结果以及递归时log2n的栈空间，
 * 空间复杂度O(n+logn)
 * 稳定性 稳定
 */
public class MergingSort {
    public static void main(String[] args) {
        int[] a = new int[10];
        boolean flag = true;
        //random array
        for (int i = 0; i < a.length; i++) {
            Random rd = new Random();
            a[i] = rd.nextInt(10);
        }

        System.out.println("Random Array :");
        System.out.println(Arrays.toString(a));
        System.out.println();
        System.out.println("Merging Sort :");

        //归并排序开始
        mergingSort(a, a, 0, a.length - 1);

        System.out.println(Arrays.toString(a));
    }

    //递归方法，实现对序列的分割和合并
    //传入的是待排序列，要生成的新序列，序列开始和结尾角标
    public static void mergingSort(int[] oldArray, int[] newArray, int start, int end) {
        //定义该值将序列从中间分割
        int mid;
        //定义一个临时数组容纳被分割的数组
        int[] tempArray = new int[oldArray.length];
        //如果数组头角标==尾角标，则序列待排仅一个元素，新序列就是旧序列
        //推理可知，递归进行到最后，序列一定会被分割成单个元素
        if (start == end) {
            newArray[start] = oldArray[start];
        } else {
            //取中值
            mid = (start + end) / 2;
            //分别对新分割好的序列进行分割
            mergingSort(oldArray, tempArray, start, mid);
            mergingSort(oldArray, tempArray, mid + 1, end);
            //对分割好的序列进行排序和合并操作
            merge(tempArray, newArray, start, mid, end);

        }
    }

    public static void merge(int[] tempArray, int[] newArray, int start, int mid, int end) {
        int j, k, l;
        //已知每个序列被分割成了两个序列，左和右序列
        //从左和右序列的最小角标开始，依次进行比较
        for (j = mid + 1, k = start; start <= mid && j <= end; k++) {
            //temp[i]到temp[mid]左序列 temp[mid+1]到temp[end]右序列
            //左序列当前元素小，就先放到新序列里 反之，右序列当前元素先放入
            //该句元素比较代码说明了其是稳定的
            if (tempArray[start] < tempArray[j]) {
                newArray[k] = tempArray[start++];
            } else {
                newArray[k] = tempArray[j++];
            }
        }
        //上个操作完成后，左或右序列可能有剩余，继续讲剩余元素补充到新序列中
        if (start <= mid) {
            for (l = 0; l <= mid - start; l++) {
                newArray[k + l] = tempArray[start + l];
            }
        }
        if (j <= end) {
            for (l = 0; l <= end - j; l++) {
                newArray[k + l] = tempArray[j + l];
            }
        }
    }
}
```

#### 参考

[GeeksforGeeks：https://www.geeksforgeeks.org/merge-sort/](https://www.geeksforgeeks.org/merge-sort/)

[十大经典排序算法：https://www.cnblogs.com/onepixel/articles/7674659.html](https://www.cnblogs.com/onepixel/articles/7674659.html)

[《大话数据结构》：https://book.douban.com/subject/6424904/](https://book.douban.com/subject/6424904/)