package week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P94_Tree_InOrder {

    // 迭代
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || deque.size() != 0) {
            while (curr != null) {
                deque.addFirst(curr);
                curr = curr.left;
            }
            list.add(deque.getFirst().val);
            list.add(curr.val);
            curr = curr.right;
        }
        return list;
    }

    // 递归方式
    public void recursive(TreeNode root, List<Integer> list) {
        if (root != null) {
            if (root.left != null) {
                recursive(root.left, list);
            }
            list.add(root.val);
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
