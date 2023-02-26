package sprint6.max_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaxDistance {
    static int[] distances;
    static boolean[] visited;

    static int biggestDistance = 0;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, LinkedList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            distances = new int[vertexes + 1];
//            Arrays.fill(distances, -1);
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
            int from = Integer.parseInt(reader.readLine());
            visited = new boolean[adjacentList.keySet().size() + 1];

            findMaxDistance(adjacentList, from);

//            Arrays.sort(distances, (o1, o2) -> Integer.compare(o2, o1));
            System.out.print(/*distances[0]*/biggestDistance);
        }
    }

    private static void findMaxDistance(HashMap<Integer, LinkedList<Integer>> map, int from) {
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> list;
//        distances[from] = 0;
        queue.push(from);

        while (!queue.isEmpty()) {
            int el = queue.poll();
            if (el >= visited.length || visited[el]) continue;
            visited[el] = true;
            list = map.get(el);
            if (list == null) return;
            while (!list.isEmpty()) {
                int newElement = list.poll();
                if (!visited[newElement] && distances[newElement] == /*-1*/0) {
                    distances[newElement] = distances[el] + 1;
                    if (distances[newElement] > biggestDistance) biggestDistance = distances[newElement];
                }
                if (!visited[newElement]) queue.add(newElement);
            }
        }
    }
}
