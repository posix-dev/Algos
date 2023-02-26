package sprint6.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS {

    static String[] colors;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, LinkedList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());

            colors = new String[vertexes];
            for (int i = 0; i < vertexes; i++) colors[i] = "white";

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
            int startNumber = Integer.parseInt(reader.readLine());

            dfs(adjacentList, startNumber);
        }
    }

    private static void dfs(HashMap<Integer, LinkedList<Integer>> list, int startNumber) {
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> list1 = list.get(startNumber);

        if (list1 == null || list1.isEmpty()) {
            System.out.println(startNumber);
            return;
        }

        stack.push(startNumber);

        while (!stack.isEmpty()) {
            int value = stack.pop();

            if (Objects.equals(colors[value - 1], "white")) {
                colors[value - 1] = "gray";
                System.out.print(value + " ");
                LinkedList<Integer> adjacentList = list.get(value);

                if (adjacentList == null || adjacentList.isEmpty()) continue;

                adjacentList.sort((o1, o2) -> -Integer.compare(o1, o2)); // reverse order for stack

                // take the most minimum value first
                while (!adjacentList.isEmpty()) stack.push(adjacentList.poll());
            } else {
                colors[value - 1] = "black";
            }
        }
    }

    void DFSUtil(int v, HashMap<Integer, LinkedList<Integer>> map) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int n : map.get(v)) {
            if (!visited[n]) DFSUtil(n, map);
        }
    }
}
