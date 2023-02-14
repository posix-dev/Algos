package sprint5.max_depth;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static int treeSolution(Node head) {
        if (head == null) return 0;

        int leftSize = findSize(head.left, 0);
        int rightSize = findSize(head.right, 0);

        return Math.max(leftSize, rightSize) + 1;
    }

    private static int findSize(Node root, int counter) {
        if (root == null) return counter;

        int left = findSize(root.left, counter + 1);
        int right = findSize(root.right, counter + 1);

        return Math.max(left, right);
    }

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5) == 3;
        System.out.println(treeSolution(node5));
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
