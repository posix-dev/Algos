package sprint5.search_tree;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static boolean treeSolution(Node head) {
        return isBST(head);
    }
    private static boolean isBST(Node head) {
        if (head == null) return true;

        boolean leftRes = head.value > getMaxValue(head.left);
        boolean rightRes = head.value < getMinValue(head.right);

        return leftRes && rightRes && isBST(head.left) && isBST(head.right);
    }

    private static int getMinValue(Node head) {
        if (head == null) return Integer.MAX_VALUE;

        int result = head.value;
        int lRes = getMinValue(head.left);
        int rRes = getMinValue(head.right);

        if (lRes < result) result = lRes;
        if (rRes < result) result = rRes;

        return result;
    }

    private static int getMaxValue(Node head) {
        if (head == null) return Integer.MIN_VALUE;

        int result = head.value;
        int lRes = getMaxValue(head.left);
        int rRes = getMaxValue(head.right);

        if (lRes > result) result = lRes;
        if (rRes > result) result = rRes;

        return result;
    }


    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        System.out.println(treeSolution(node5));
        node2.value = 5;
        assert !treeSolution(node5);
        System.out.println(treeSolution(node5));
    }

//    private static void test2() {
//        Node node3 = new Node(1, null, null);
//        Node node4 = new Node(4, null, null);
//        Node node1 = new Node(3, node3, node4);
//        Node node5 = new Node(6, null, null);
//        Node node9 = new Node(140, null, null);
//        Node node10 = new Node(159, null, null);
//        Node node8 = new Node(139, node9, node10);
//        Node node7 = new Node(19, null, node8);
//        Node node6 = new Node(9, null, node7);
//        Node node2 = new Node(8, node5, node6);
//        Node node0 = new Node(5, node1, node2);
//
//        System.out.println(treeSolution(node0));
//    }

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
