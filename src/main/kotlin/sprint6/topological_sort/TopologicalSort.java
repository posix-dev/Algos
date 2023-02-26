package sprint6.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, ArrayList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            visited = new boolean[vertexes + 1];

            adjacentList = new HashMap<>();
            edges = Integer.parseInt(tokenizer.nextToken());
            int vertex, edge;
            ArrayList<Integer> queue;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                edge = Integer.parseInt(tokenizer.nextToken());

                if (adjacentList.containsKey(vertex)) {
                    queue = adjacentList.get(vertex);
                } else {
                    queue = new ArrayList<>();
                }

                queue.add(edge);
                adjacentList.put(vertex, queue);
            }

            for (int i = 1; i < vertexes + 1; i++) {
                if (!visited[i]) goDFS(adjacentList, i);
            }

            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }

    private static void goDFS(HashMap<Integer, ArrayList<Integer>> map, int startPosition) {
        visited[startPosition] = true;

        ArrayList<Integer> list = map.get(startPosition);
        if (list != null) {
            for (int value : list) {
                if (!visited[value]) goDFS(map, value);
            }
        }

        stack.push(startPosition);
    }
}
