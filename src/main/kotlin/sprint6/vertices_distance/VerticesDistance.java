package sprint6.vertices_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class VerticesDistance {

    static int[] distances;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, LinkedList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            distances = new int[vertexes + 1];
            Arrays.fill(distances, -1);
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
            StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer2.nextToken());
            int to = Integer.parseInt(tokenizer2.nextToken());
            visited = new boolean[adjacentList.keySet().size() + 1];

            if (from == to) {
                System.out.print(0);
                return;
            }

            findDistance(adjacentList, from, to);
            System.out.print(distances[to]);
        }
    }

    private static void findDistance(HashMap<Integer, LinkedList<Integer>> map, int from, int to) {
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> list;
        distances[from] = 0;
        queue.push(from);

        while (!queue.isEmpty()) {
            int el = queue.poll();
            if (visited[el]) continue;
            visited[el] = true;
            list = map.get(el);
            if (list == null) return;
            while (!list.isEmpty()) {
                int newElement = list.poll();
                if (!visited[newElement] && distances[newElement] == -1) {
                    distances[newElement] = distances[el] + 1;
                }
                if (newElement == to) return;
                if (!visited[newElement]) queue.add(newElement);
            }
        }
    }
}
