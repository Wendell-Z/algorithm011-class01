package Week03;

import java.util.HashMap;
import java.util.Map;

public class P105_Construct_Binary_Tree {
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        map = new HashMap(length);
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        return recursive(preorder, inorder, 0, length - 1, 0, length - 1);
    }

    public TreeNode recursive(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) return null;
        // 前序中根节点的角标
        int root_preorder_index = preorder_left;
        // 中序中根节点的角标
        int root_inorder_index = map.get(preorder[root_preorder_index]);
        // 根节点
        TreeNode root = new TreeNode(preorder[root_preorder_index]);
        // 确定左右子树范围
        int pre_left_start = root_preorder_index + 1;
        int pre_left_end = root_inorder_index - inorder_left + root_preorder_index;
        int in_left_start = inorder_left;
        int in_left_end = root_inorder_index - 1;
        root.left = recursive(preorder, inorder, pre_left_start, pre_left_end, in_left_start, in_left_end);
        root.right = recursive(preorder, inorder, pre_left_end + 1, preorder_right, root_inorder_index + 1, inorder_right);
        return root;
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
