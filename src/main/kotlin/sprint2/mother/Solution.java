package sprint2.mother;

import assistant.Node;

import java.util.Objects;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static int solution(Node<String> head, String elem) {
        if (head == null) return -1;
        int index = 0;

        while (head != null) {
            if(Objects.equals(head.value, elem)) {
                return index;
            }

            index++;
            head = head.next;
        }

        return -1;
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        int idx = solution(node0, "node2");
        assert idx == 2;
    }
}