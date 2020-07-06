package week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class P589_NTree_PreOrder {
    // 迭代
    public List<Integer> preorder(Node root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            Node curr = deque.removeFirst();
            list.add(curr.val);
            //以栈的形式放 先入后出 先出左所以先进右
            for (int i = curr.children.size() - 1; i >= 0; i--) {
                deque.addFirst(curr.children.get(i));
            }
        }
        return list;
    }
    // 递归
    public void recursive(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            for (int i = 0; i < root.children.size(); i++) {
                if (root.children.get(i) != null) recursive(root.children.get(i), list);
            }
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
