package sprint5.lamps;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static int treeSolution(Node head) {
        if (head == null) return Integer.MIN_VALUE;

        int res = head.value;
        int left = treeSolution(head.left);
        int right = treeSolution(head.right);

        if (left > res) res = left;
        if (right > res) res = right;

        return res;
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
        System.out.println(treeSolution(node4));
    }


//      Comment it before submitting
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
