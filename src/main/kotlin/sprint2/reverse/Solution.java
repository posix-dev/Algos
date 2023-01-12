package sprint2.reverse;

import assistant.Node2;

public class Solution {

    public static void main(String[] args) {
        test();
    }
    public static Node2<String> solution(Node2<String> head) {
        Node2<String> next;
        Node2<String> prev = null;
        Node2<String> current = head;

        while (current != null) {
            next = current.next;
            current.next = prev;
            current.prev = next;

            prev = current;
            current = next;
        }

        return prev;
    }

    private static void test() {
        Node2<String> node3 = new Node2<>("node3", null, null);
        Node2<String> node2 = new Node2<>("node2", node3, null);
        Node2<String> node1 = new Node2<>("node1", node2, null);
        Node2<String> node0 = new Node2<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node2<String> newNode = solution(node0);
        /* result is :*/
        assert newNode == node3;
        assert node3.next == node2;
        assert node2.next == node1;
        assert node2.prev == node3;
        assert node1.next == node0;
        assert node1.prev == node2;
        assert node0.prev == node1;
    }
}
