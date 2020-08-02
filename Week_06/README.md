学习笔记

# Week06

总结自 https://labuladong.gitbook.io/algo/

**动态规划问题的一般形式是求最值**。

**求解动态规划的核心问题是穷举**。

动态规划这类问题**存在「重叠子问题」**，如果暴力穷举的话效率会极其低下，所以需要「备忘录」或者「DP table」优化穷举过程，避免不必要的计算。而且，动态规划问题一定会**具备「最优子结构」**，才能通过子问题的最值得到原问题的最值。

另外，虽然动态规划的核心思想就是穷举求最值，但问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出**正确的「状态转移方程」**才能正确地穷举。

**重叠子问题、最优子结构、状态转移方程**就是**动态规划三要素**。

**明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义**。

按上面的套路走，最后的结果就可以套这个框架：

```
# 初始化 base case
dp[0][0][...] = base
# 进行状态转移
for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
        for ...
            dp[状态1][状态2][...] = 求最值(选择1，选择2...)
```

# 斐波那契数列

**1、暴力递归**

斐波那契数列的数学形式就是递归的，写成代码就是这样：

```
int fib(int N) {
    if (N == 1 || N == 2) return 1;
    return fib(N - 1) + fib(N - 2);
}
```

递归树：

![img](https://gblobscdn.gitbook.com/assets%2F-LrtQOWSnDdXhp3kYN4k%2Fsync%2F70af23686865b425f7b88f172ac696878985f4c6.jpg?alt=media)





**用子问题个数乘以解决一个子问题需要的时间。**

算法低效的原因：存在大量重复计算，比如 `f(18)` 被计算了两次，而且你可以看到，以 `f(18)` 为根的这个递归树体量巨大，多算一遍，会耗费巨大的时间。更何况，还不止 `f(18)` 这一个节点被重复计算，所以这个算法及其低效。

这就是动态规划问题的第一个性质：**重叠子问题**。

**2、带备忘录的递归解法**

即然重复计算，那么可以造一个「备忘录」，每次算出某个子问题的答案先记到「备忘录」里再返回；每次遇到一个子问题先去「备忘录」里查一查。

```c++
int fib(int N) {    
    if (N < 1) return 0;    
    // 备忘录全初始化为 0    
    vector<int> memo(N + 1, 0);    
    // 进行带备忘录的递归    
    return helper(memo, N);}
	int helper(vector<int>& memo, int n) {    
        // base case     
        if (n == 1 || n == 2) return 1;    
        // 已经计算过   
        if (memo[n] != 0) return memo[n];    
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);    
        return me mo[n];
    }
```

![img](https://gblobscdn.gitbook.com/assets%2F-LrtQOWSnDdXhp3kYN4k%2Fsync%2Fe6547795e536cbd16b7f5f23da637032cc88e87e.jpg?alt=media)

![img](https://gblobscdn.gitbook.com/assets%2F-LrtQOWSnDdXhp3kYN4k%2Fsync%2F6d82de2baa7ef942d20da7d9323d86cf55051f67.jpg?alt=media)

**递归算法的时间复杂度怎么计算？就是用子问题个数乘以解决一个子问题需要的时间。**

子问题个数，即图中节点的总数，由于本算法不存在冗余计算，子问题就是 `f(1)`, `f(2)`, `f(3)` ... `f(20)`，数量和输入规模 n = 20 成正比，所以子问题个数为 O(n)。

解决一个子问题的时间，同上，没有循环，时间为 O(1)。本算法的时间复杂度是 O(n)。

至此，带备忘录的递归解法的效率已经和迭代的动态规划解法一样了。实际上，这种解法和迭代的动态规划已经差不多了，只不过这种方法叫做「自顶向下」，动态规划叫做「自底向上」。

「自顶向下」-注意我们刚才画的递归树（或者说图），是从上向下延伸，都是从一个规模较大的原问题比如说 `f(20)`，向下逐渐分解规模，直到 `f(1)` 和 `f(2)` 这两个 base case，然后逐层返回答案，这就叫「自顶向下」。

「自底向上」-反过来，我们直接从最底下，最简单，问题规模最小的 `f(1)` 和 `f(2)` 开始往上推，直到推到我们想要的答案 `f(20)`，这就是动态规划的思路，这也是为什么动态规划一般都脱离了递归，而是由循环迭代完成计算。

**3、dp 数组的迭代解法**

把这个「备忘录」独立出来成为一张表，叫做 DP table 

```c++
int fib(int N) {
    vector<int> dp(N + 1, 0);
    // base case
    dp[1] = dp[2] = 1;
    for (int i = 3; i <= N; i++)
        dp[i] = dp[i - 1] + dp[i - 2];
    return dp[N];
}
```

![img](https://gblobscdn.gitbook.com/assets%2F-LrtQOWSnDdXhp3kYN4k%2Fsync%2F65192fe4cbb8d583daf45ceb260b2d333ba77169.jpg?alt=media)

这里，引出「状态转移方程」这个名词，实际上就是描述问题结构的数学形式：

![img](https://gblobscdn.gitbook.com/assets%2F-LrtQOWSnDdXhp3kYN4k%2Fsync%2F6b54539705dc61842749cf10bfa80e367c912664.png?alt=media)

把 `f(n)` 想做一个状态 `n`，这个状态 `n` 是由状态 `n - 1` 和状态 `n - 2` 相加转移而来。



**千万不要看不起暴力解，动态规划问题最困难的就是写出这个暴力解，即状态转移方程**。只要写出暴力解，优化方法无非是用备忘录或者 DP table，再无奥妙可言。

根据斐波那契数列的状态转移方程，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，只要想办法存储之前的两个状态就行了。所以，可以进一步优化，把空间复杂度降为 O(1)：

```c++
int fib(int n) {
    if (n == 2 || n == 1) 
        return 1;
    int prev = 1, curr = 1;
    for (int i = 3; i <= n; i++) {
        int sum = prev + curr;
        prev = curr;
        curr = sum;
    }
    return curr;
}
```

「**状态压缩**」，如果我们发现每次状态转移只需要 DP table 中的一部分，那么可以尝试用状态压缩来缩小 DP table 的大小，只记录必要的数据，上述例子就相当于把DP table 的大小从 `n` 缩小到 2。