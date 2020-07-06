package week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class P429_NTree_LevelOrder {

    public List<List<Integer>> levelOrder(Node root) {      
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;
    }


    class Node {
        public int val;
        public List<P589_NTree_PreOrder.Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<P589_NTree_PreOrder.Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
