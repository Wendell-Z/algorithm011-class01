package week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class P144_Tree_PreOrder {
    // 迭代
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        while (curr != null || !deque.isEmpty()) {
            while (curr != null) {
                res.add(curr.val);
                deque.add(curr);
                curr = curr.left;
            }
            curr = deque.removeLast();
            curr = curr.right;
        }
        return res;
    }
    // 递归
    public void recursive(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            if (root.left != null) {
                recursive(root.left, list);
            }
            if (root.right != null) {
                recursive(root.right, list);
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
