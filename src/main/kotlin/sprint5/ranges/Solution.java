package sprint5.ranges;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void printRange(Node root, int L, int R, BufferedWriter writer) throws IOException {
        if (root == null) return;

        findMe(root, L, R);

        Collections.sort(list);

        for (int value : list) {
            System.out.println(value);
        }
    }

    private static void findMe(Node root, int l, int r) {
        if (root == null) return;

        findMe(root.getLeft(), l, r);
        findMe(root.getRight(), l, r);

        if (root.getValue() >= l && root.getValue() <= r) {
            list.add(root.getValue());
        }
    }

    private static void test() throws IOException {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(null, node1, 1);
        Node node3 = new Node(null, null, 8);
        Node node4 = new Node(null, node3, 8);
        Node node5 = new Node(node4, null, 9);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node2, node6, 5);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        printRange(node7, 2, 8, writer);  // expected output: 2 5 8 8
        writer.flush();
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
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
    }
}
