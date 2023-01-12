package sprint2.unlovely_business;

import assistant.Node;

public class Solution {

    public static void main(String[] args) {
        test();
    }
    public static Node<String> solution(Node<String> head, int idx) {
        Node<String> temp = head;

        // If head needs to be removed
        if (idx == 0) {
            head = temp.next; // Change head
            return head;
        }

        while (idx - 1 != 0) {
            idx--;
            temp = temp.next;
        }

        if (temp == null || temp.next == null) return head;

        temp.next = temp.next.next;

        return head;
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 0);
        assert newHead.equals(node0);
        assert newHead.next == node2;
        assert newHead.next.next == node3;
        assert newHead.next.next.next == null;
        // result is : node0 -> node2 -> node3
    }
}