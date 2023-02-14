package sprint5.max_tree_path;

public class Solution {

    static int[] res;

    public static void main(String[] args) {
//        test();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();
//        test13();
//        test14();
    }

    public static int treeSolution(Node head) {
        if (head == null) return 0;
        if (head.left == null && head.right == null) return head.value;
        res = new int[]{Integer.MIN_VALUE};

        int result;
        int leftSum = findBiggestSum(head.left, res);
        int rightSum = findBiggestSum(head.right, res);

        result = Math.max(leftSum + head.value, head.value);
        result = Math.max(result, Math.max(result + rightSum, rightSum));

        return Math.max(result, res[0]);
    }

    public static int findBiggestSum(Node root, int[] res) {
        if (root == null) return Integer.MIN_VALUE;

        int value = root.value;
        int left = findBiggestSum(root.left, res);
        int right = findBiggestSum(root.right, res);
        int biggestResult = Math.max(left, right);
        res[0] = Math.max(res[0], root.value + left + right);
        int result;
        if (left <= 0 && right <= 0) {
            result = Math.max(value, biggestResult);
        } else {
            result = value + biggestResult;
        }

        return result;
    }

    private static void test() {
        Node node1 = new Node(5, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(-3, node2, node1);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(2, node4, node3);
        assert treeSolution(node5) == 6;
        System.out.println(treeSolution(node5));
    }

    private static void test2() {
        Node node10 = new Node(-6, null, null);
        Node node9 = new Node(9, null, null);
        Node node8 = new Node(-1, null, null);
        Node node7 = new Node(0, null, null);
        Node node6 = new Node(4, null, null);
        Node node5 = new Node(3, node9, node10);
        Node node4 = new Node(7, node8, null);
        Node node2 = new Node(2, node4, node5);
        Node node3 = new Node(3, node6, node7);
        Node node1 = new Node(-1, node2, node3);

        System.out.println(treeSolution(node1));
    }

    private static void test3() {
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(1, null, null);
        Node node1 = new Node(1, node2, node3);

        System.out.println(treeSolution(node1));
    }

    private static void test4() {
        Node node1 = new Node(-1, null, null);

        System.out.println(treeSolution(node1));
    }

    private static void test5() {
        Node node10 = new Node(9, null, null);
        Node node9 = new Node(8, node10, null);
        Node node8 = new Node(7, null, null);
        Node node7 = new Node(6, null, null);
        Node node6 = new Node(25, null, null);
        Node node5 = new Node(4, node8, node9);
        Node node4 = new Node(3, node6, node7);
        Node node3 = new Node(24, null, node5);
        Node node2 = new Node(1, node4, null);
        Node node1 = new Node(-10, node2, node3);

        assert treeSolution(node1) == 64;
        System.out.println(treeSolution(node1));
    }

    private static void test6() {
        Node node3 = new Node(-3, null, null);
        Node node2 = new Node(-2, null, null);
        Node node1 = new Node(-1, node2, node3);
        assert treeSolution(node1) == -1;
        System.out.println(treeSolution(node1));
    }

    private static void test7() {
        Node node5 = new Node(7, null, null);
        Node node4 = new Node(15, null, null);
        Node node3 = new Node(20, node4, node5);
        Node node2 = new Node(9, null, null);
        Node node1 = new Node(-10, node2, node3);

        assert treeSolution(node1) == 42;
        System.out.println(treeSolution(node1));
    }

    private static void test8() {
        Node node5 = new Node(-7, null, null);
        Node node4 = new Node(-15, null, null);
        Node node3 = new Node(-20, node4, node5);
        Node node2 = new Node(-9, null, null);
        Node node1 = new Node(-10, node2, node3);

        assert treeSolution(node1) == -7;
        System.out.println(treeSolution(node1));
    }

    private static void test9() {
        Node node3 = new Node(3, null, null);
        Node node2 = new Node(-2, null, null);
        Node node1 = new Node(-1, node2, node3);
        assert treeSolution(node1) == 3;
        System.out.println(treeSolution(node1));
    }

    private static void test10() {
        Node node5 = new Node(-7, null, null);
        Node node4 = new Node(15, null, null);
        Node node3 = new Node(20, node4, node5);
        Node node2 = new Node(-9, null, null);
        Node node1 = new Node(-10, node2, node3);

        assert treeSolution(node1) == 35;
        System.out.println(treeSolution(node1));
    }

    private static void test11() {
        Node node15 = new Node(1114, null, null);
        Node node14 = new Node(118, null, null);
        Node node13 = new Node(6, null, null);
        Node node12 = new Node(7, null, null);
        Node node11 = new Node(12, null, null);
        Node node10 = new Node(10, null, null);
        Node node9 = new Node(-2, null, null);
        Node node8 = new Node(-3, null, null);
        Node node7 = new Node(1005, node14, node15);
        Node node6 = new Node(0, node12, node13);
        Node node5 = new Node(4, node10, node11);
        Node node4 = new Node(3, node8, node9);
        Node node3 = new Node(1, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node1 = new Node(5, node2, node3);

        assert treeSolution(node1) == 2237;
        System.out.println(treeSolution(node1));
    }

    private static void test12() {
        Node node15 = new Node(1, null, null);
        Node node14 = new Node(8, null, null);
        Node node12 = new Node(7, null, null);
        Node node11 = new Node(12, null, null);
        Node node10 = new Node(10, null, null);
        Node node13 = new Node(6, null, null);
        Node node9 = new Node(-2, null, null);
        Node node8 = new Node(-3, null, null);
        Node node7 = new Node(1005, node14, node15);
        Node node6 = new Node(0, node12, node13);
        Node node5 = new Node(4, node10, node11);
        Node node4 = new Node(3, node8, node9);
        Node node3 = new Node(1, node6, node7);
        Node node2 = new Node(112, node4, node5);
        Node node1 = new Node(-105, node2, node3);

        assert treeSolution(node1) == 1037;
        System.out.println(treeSolution(node1));
    }

    private static void test13() {
        Node node15 = new Node(-1, null, null);
        Node node14 = new Node(-8, null, null);
        Node node12 = new Node(-7, null, null);
        Node node11 = new Node(-12, null, null);
        Node node10 = new Node(-10, null, null);
        Node node13 = new Node(-6777, null, null);
        Node node9 = new Node(-2666, null, null);
        Node node8 = new Node(-3, null, null);
        Node node7 = new Node(-1005, node14, node15);
        Node node6 = new Node(0, node12, node13);
        Node node5 = new Node(-4455, node10, node11);
        Node node4 = new Node(-3, node8, node9);
        Node node3 = new Node(-1, node6, node7);
        Node node2 = new Node(-112, node4, node5);
        Node node1 = new Node(-105, node2, node3);

        assert treeSolution(node1) == 0;
        System.out.println(treeSolution(node1));
    }

    private static void test14() {
        Node node15 = new Node(-1, null, null);
        Node node14 = new Node(-8, null, null);
        Node node12 = new Node(-7, null, null);
        Node node11 = new Node(-12, null, null);
        Node node10 = new Node(-10, null, null);
        Node node13 = new Node(-6777, null, null);
        Node node9 = new Node(-2666, null, null);
        Node node8 = new Node(-3, null, null);
        Node node7 = new Node(-1005, node14, node15);
        Node node6 = new Node(-6, node12, node13);
        Node node5 = new Node(-4455, node10, node11);
        Node node4 = new Node(-3, node8, node9);
        Node node3 = new Node(-1, node6, node7);
        Node node2 = new Node(-112, node4, node5);
        Node node1 = new Node(-105, node2, node3);

        assert treeSolution(node1) == -1;
        System.out.println(treeSolution(node1));
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
