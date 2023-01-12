package sprint2.business_list;

import assistant.Node;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static void solution(Node<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }

}

