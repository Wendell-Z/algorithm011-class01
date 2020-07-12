package Week03;

public class P236_Lowest_Common_Ancestor {
    private TreeNode res;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = null;
        contains(root, p, q);
        return res;
    }

    private boolean contains(TreeNode x, TreeNode p, TreeNode q) {
        if (x == null) return false;
        boolean leftCon = contains(x.left, p, q);
        boolean rightCon = contains(x.right, p, q);
        if ((x == p && rightCon) || (x == q && leftCon) || (x == p && leftCon) || (x == q && rightCon) || (leftCon && rightCon)) {
            res = x;
            return true;
        }
        return x == p || x == q || leftCon || rightCon;
    }

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
