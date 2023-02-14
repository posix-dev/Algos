package sprint5.balanced_tree;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static boolean treeSolution(Node head) {
        if (head == null) return false;

        int leftSize = findSize(head.left, 0);
        int rightSize = findSize(head.right, 0);

        return Math.abs(leftSize - rightSize) < 2;
    }

    private static int findSize(Node root, int counter) {
        if (root == null) return counter;

        int left = findSize(root.left, counter + 1);
        int right = findSize(root.right, counter + 1);

        return Math.max(left, right);
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
        System.out.println(treeSolution(node5));
    }

    //Comment it before submitting
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
