package sprint5.homework.delete_node;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал алгоритм удаления узла из BST дерева.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98795/topics/e7dbf42a-fd5a-434b-990d-9cfe0e3a10c8/lessons/03eb9b46-4c74-43b4-8d00-a125aeed47bf/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * В данном алгоритме реализовано удаление узла и упорядочение BST дерева, чтобы оно не сломалось.
 * Для этого берем при удалении в следующем либо min эл-т из правого поддерева или max из левого поддерева.
 * В методе remove проходим и записываем путь для рута и если находим наш эл-т то берем max из левого(если есть, иначе
 * правый min эл-т) и далее идём до этого эл-та(min/max), чтобы удалить его, тк изначально мы переписали value, но
 * сам эл-т живет. В конце когда мы удалим эл-т, удаление произошло успешно.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм будет работать в среднем за O(logN) или же O(H). Вот здесь не понял, тк по сути высота это и есть logN
 * В худшем случае может быть, к примеру, односвязный список и сложность будет равна O(N);
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Данный алгоритм будет занимать O(N) памяти для Node-ов + O(logN) памяти рекурсии =>
 * O(N) * O(logN) => O(N*logN)
 */

// Id: 82140407
// Link: https://contest.yandex.ru/contest/24810/run-report/82140407/

public class Solution {

    public static void main(String[] args) {
        test3();
    }

    public static Node remove(Node root, int key) {
        if (root == null || (root.getRight() == null && root.getLeft() == null)) return null;

        if (root.getValue() > key) {
            root.setLeft(remove(root.getLeft(), key));
        } else if (root.getValue() < key) {
            root.setRight(remove(root.getRight(), key));
        } else {
            Node max = getMaxValue(root.getLeft());

            if (max == null) { // if we didn't find max value from left then we have to find right one
                max = getMinValue(root.getRight());
                root.setValue(max.getValue());
                root.setRight(remove(root.getRight(), max.getValue()));
            } else {
                root.setValue(max.getValue());
                root.setLeft(remove(root.getLeft(), max.getValue()));
            }
        }

        return root;
    }

    private static Node getMinValue(Node head) {
        if (head == null) return null;

        while (head.getLeft() != null) {
            head = head.getLeft();
        }

        return head;
    }

    private static Node getMaxValue(Node head) {
        if (head == null) return null;

        while (head.getRight() != null) {
            head = head.getRight();
        }

        return head;
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

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
        System.out.println(newHead);
    }

    private static void test2() {
        Node node10 = new Node(null, null, 99);
        Node node9 = new Node(null, null, 72);
        Node node8 = new Node(node9, node10, 91);
        Node node7 = new Node(null, null, 50);
        Node node6 = new Node(null, null, 32);
        Node node5 = new Node(null, node6, 29);
        Node node4 = new Node(null, null, 11);
        Node node3 = new Node(node7, node8, 65);
        Node node2 = new Node(node4, node5, 20);
        Node node1 = new Node(node2, node3, 41);
        Node newHead = remove(node1, 41);
        System.out.println(newHead);
    }

    private static void test3() {
        Node node10 = new Node(null, null, 840);
        Node node9 = new Node(null, null, 708);
        Node node8 = new Node(null, null, 609);
        Node node7 = new Node(null, node8, 568);
        Node node6 = new Node(node7, null, 626);
        Node node5 = new Node(node6, node9, 649);
        Node node4 = new Node(node5, node10, 818);
        Node node3 = new Node(null, null, 270);
        Node node2 = new Node(node3, null, 355);
        Node node1 = new Node(node2, node4, 460);
        Node newHead = remove(node1, 568);
        System.out.println(newHead);
    }
}
