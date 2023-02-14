package sprint5.tree_anagramm;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static boolean treeSolution(Node head) {
        if (head == null) return true;

        return isAnagram(head.left, head.right);
    }

    private static boolean isAnagram(Node left, Node right) {
        if (left == null || right == null) return left == right;

        boolean left1 = isAnagram(left.left, right.right);
        boolean right1 = isAnagram(left.right, right.left);

        return left.value == right.value && left1 && right1;
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


    private static void test() {
        Node node1 = new Node(3, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(4, null, null);
        Node node4 = new Node(3, null, null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        assert treeSolution(node7);
        System.out.println(treeSolution(node7));
    }
}
