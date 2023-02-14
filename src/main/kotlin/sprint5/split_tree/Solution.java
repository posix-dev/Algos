package sprint5.split_tree;

import java.util.ArrayList;
import java.util.List;


public class Solution {

    static ArrayList<Node> nodes = new ArrayList<>();

    public static void main(String[] args) {
        test();
    }

    public static List<Node> split(Node root, int k) {
        if (root == null) return new ArrayList<>();

        nodes.add(getNode(root, k));

        return nodes;
    }

    private static Node getNode(Node root, int k) {
        if (root == null) return new Node(null, null, 0, 0);

        if (root.left != null && root.left.size + 1 > k) {
            k -= root.left.size + 1;
            root.right = getNode(root.right, k);
        }

        root = getNode(root.left, k - 1);

        return root;
    }


    public static void test() {
        Node node1 = new Node(null, null, 3, 1);
        Node node2 = new Node(null, node1, 2, 2);
        Node node3 = new Node(null, null, 8, 1);
        Node node4 = new Node(null, null, 11, 1);
        Node node5 = new Node(node3, node4, 10, 3);
        Node node6 = new Node(node2, node5, 5, 6);
        List<Node> res = split(node6, 4);
        assert res.get(0).getSize() == 4;
        assert res.get(1).getSize() == 2;
    }

    private static class Node {

        private Node left;
        private Node right;
        private int value;
        private int size;

        Node(Node left, Node right, int value, int size) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.size = size;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
