package sprint5.number_path;

import java.util.ArrayList;

public class Solution {

    static ArrayList<Integer> results;

    public static void main(String[] args) {
        test2();
    }

    public static int treeSolution(Node head) {
        if (head == null) return 0;
        results = new ArrayList<>();

        goAll(head, "");
        int result = 0;

        for (int res : results) {
            result += res;
        }

        return result;
    }

    private static void goAll(Node head, String val) {
        if (head == null) return;

        int headValue = head.value;

        if (head.left == null && head.right == null) {
            results.add(Integer.parseInt(val + headValue));
            return;
        }

        goAll(head.left, val + headValue);
        goAll(head.right, val + headValue);
    }

    private static void test() {
        Node node1 = new Node(2, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);
        assert treeSolution(node5) == 275;
        System.out.println(treeSolution(node5));
    }

    private static void test2() {
        Node node1 = new Node(3, null, null);
        Node node2 = new Node(5, null, null);
        Node node3 = new Node(1, node1, node2);
        assert treeSolution(node3) == 28;
        System.out.println(treeSolution(node3));
    }


    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
