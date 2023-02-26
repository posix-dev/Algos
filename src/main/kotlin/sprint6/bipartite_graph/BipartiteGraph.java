package sprint6.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;

public class BipartiteGraph {

    private static final String WHITE = "white";
    private static final String BLUE = "blue";
    private static final String RED = "red";
    static String[] colors;
    static HashMap<Integer, LinkedList<Integer>> adjacentList;

    static String finalText = "YES";

    public static void main(String[] args) throws IOException {
        int edges, vertexes;


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());

            colors = new String[vertexes];
            for (int i = 0; i < vertexes; i++) colors[i] = WHITE;

            adjacentList = new HashMap<>();
            edges = Integer.parseInt(tokenizer.nextToken());
            int vertex, edge;
            LinkedList<Integer> queue;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                edge = Integer.parseInt(tokenizer.nextToken());

                if (adjacentList.containsKey(vertex)) {
                    queue = adjacentList.get(vertex);
                } else {
                    queue = new LinkedList<>();
                }

                queue.add(edge);
                adjacentList.put(vertex, queue);

                if (adjacentList.containsKey(edge)) {
                    queue = adjacentList.get(edge);
                } else {
                    queue = new LinkedList<>();
                }

                queue.add(vertex);
                adjacentList.put(edge, queue);
            }

            for (int i = 1; i < adjacentList.size() + 1; i++) {
                if (Objects.equals(colors[i - 1], "white")) isBipartite(i, RED);
            }

            System.out.println(finalText);
        }
    }

    // dfs
    private static void isBipartite(int value, String color) {
        if (Objects.equals(colors[value - 1], "white")) {
            colors[value - 1] = color;
        }

        LinkedList<Integer> list = adjacentList.get(value);
        if (list == null) return;

        while (!list.isEmpty()) {
            int some = list.poll();

            if (Objects.equals(colors[some - 1], WHITE)) {
                isBipartite(some, getInvertedColor(color));
            } else if (Objects.equals(colors[some - 1], color)) {
                finalText = "NO";
                return;
            }
        }
    }

    private static String getInvertedColor(String color) {
        if (Objects.equals(color, RED)) {
            return BLUE;
        } else if (Objects.equals(color, BLUE)) {
            return RED;
        }

        throw new IllegalArgumentException();
    }
}
