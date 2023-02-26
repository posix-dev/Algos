package sprint6.adjacent_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AdjacentList {
    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, PriorityQueue<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            adjacentList = new HashMap<>();
            edges = Integer.parseInt(tokenizer.nextToken());
            int vertex, edge;
            PriorityQueue<Integer> queue;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                edge = Integer.parseInt(tokenizer.nextToken());

                if (adjacentList.containsKey(vertex)) {
                    queue = adjacentList.get(vertex);
                } else {
                    queue = new PriorityQueue<>();
                }

                queue.add(edge);
                adjacentList.put(vertex, queue);
            }

            for (int i = 1; i < 1 + vertexes; i++) {
                PriorityQueue<Integer> values = adjacentList.get(i);

                if (values == null) {
                    System.out.println(0);
                    continue;
                }

                System.out.print(values.size() + " ");

                while (values.size() != 0) System.out.print(values.remove() + " ");

                System.out.println();
            }
        }
    }
}
