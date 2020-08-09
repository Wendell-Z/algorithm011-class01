package week07;

public class P347_Friend_Circles {

    /**
     * 深度遍历
     *
     * @param m   矩阵
     * @param row 行下标，还有一个row就够了，因为节点是 m【row][row]
     */
    private void dfs(int[][] m, int row) {
        if (m[row][row] == 1) {
            //该学生已经有圈子了
            m[row][row] = 0;
            //遍历某个未访问过的朋友,
            for (int i = 0; i < m.length; i++) {
                if (m[i][i] == 1 && m[row][i] == 1)
                    dfs(m, i);
            }
        }
    }

    public int findCircleNum(int[][] M) {
        int len = M.length;
        if (len != M[0].length)
            return 0;
        if (len < 2)
            return len;
        int ans = 0;
        // 循环所有未被访问过的学生
        for (int i = 0; i < len; i++) {
            if (M[i][i] == 0)
                continue;
            dfs(M, i);
            ans++;
        }
        return ans;
    }


}
