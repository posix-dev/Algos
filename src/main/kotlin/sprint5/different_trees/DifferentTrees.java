package sprint5.different_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DifferentTrees {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = Integer.parseInt(reader.readLine());
            getPossibleTrees(1, number);
            System.out.println();
        }
    }

    private static ArrayList<Node> getPossibleTrees(int start, int end) {
        ArrayList<Node> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }

//        for (int i = start; i <= end; i++) {
        ArrayList<Node> left = getPossibleTrees(start, end - 1); // left
        ArrayList<Node> right = getPossibleTrees(start + 1, end); // right

        for (int i = start; i <= left.size(); i++) {
            for (int j = 0; j < right.size(); j++) {
                Node node = list.get(i);
                node.left = new Node(start);
                node.right = new Node(end);
                list.add(node);
            }
        }
        return list;
    }

    static class Node {
        int key;
        Node left, right;

        Node(int data) {
            this.key = data;
            left = right = null;
        }
    }
}
