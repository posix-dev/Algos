package sprint6.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFS {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, LinkedList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
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
            visited = new boolean[adjacentList.keySet().size() + 1];

            BFS(startNumber, adjacentList);
        }
    }

    private static boolean bfs(HashMap<Integer, LinkedList<Integer>> map, int startNumber) {
        LinkedList<Integer> list = map.get(startNumber);
        if (list == null) return false;
        if (!visited[startNumber]) System.out.print(startNumber + " ");
        list.sort(Integer::compare);
        LinkedList<Integer> previous = new LinkedList<>(list);
        visited[startNumber] = true;
        int element;

        while (!previous.isEmpty()) {
            element = previous.poll();
            if (visited[element]) continue;
            visited[element] = true;
            System.out.print(element + " ");
            list = map.get(element);
            if (list == null) continue;
            list.sort(Integer::compare);
            previous.addAll(list);
        }

        for (int i = 1; i < visited.length; i++) if (!visited[i]) return true;

        return false;
    }

    static void BFS(int s, HashMap<Integer, LinkedList<Integer>> map) {
        LinkedList<Integer> queue = new LinkedList<>();

        if(visited.length == 1) {
            System.out.print(s + " ");
            return;
        }
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            LinkedList<Integer> list = map.get(s);
            list.sort(Integer::compare);
            for (int n : list) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
