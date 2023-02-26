package sprint6.time_togo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TimeToGo {

    static int time = 0;
    static boolean[] visited;
    static int[] entries;
    static int[] leaves;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, LinkedList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            visited = new boolean[vertexes + 1];
            entries = new int[vertexes + 1];
            leaves = new int[vertexes + 1];

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
                queue.sort(Integer::compare);
                adjacentList.put(vertex, queue);
            }

            dfs(1, adjacentList);

            for (int i = 1; i < leaves.length; i++) {
                System.out.println(entries[i] + " " + leaves[i]);
            }
        }
    }

    private static void dfs(int num, HashMap<Integer, LinkedList<Integer>> map) {
        if (visited[num]) return;

        visited[num] = true;
        entries[num] = time;

        time += 1;
        LinkedList<Integer> list = map.get(num);

        if (list == null) {
            leaves[num] = time;
            time += 1;
            return;
        }

        for (int n : list) dfs(n, map);

        leaves[num] = time;
        time += 1;
    }
}
