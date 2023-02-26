package sprint6.components_count;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ComponentCount {
    static boolean[] visited;
    static int nextPosition = 0;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        HashMap<Integer, LinkedList<Integer>> adjacentList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            visited = new boolean[vertexes + 1];
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


            LinkedList<ArrayList<Integer>> list = new LinkedList<>();

            int i = 1;
            while (!isEmptyVisited(visited)) {
                int position = findClosestPosition(nextPosition + 1, vertexes, adjacentList);
                dfs(adjacentList, position, list, i);
                list.get(i).sort(Integer::compare);
                ++i;
            }

            System.out.println(i - 1);

            list.sort((o1, o2) -> {
                if (o1.isEmpty() || o2.isEmpty()) return 1;
                return Integer.compare(o1.get(0), o2.get(0));
            });
            for (int j = 1; j < list.size(); j++) {
                ArrayList<Integer> some = list.get(j);
                for (int value : some) System.out.print(value + " ");
                System.out.println();
            }
        }
    }

    private static boolean isEmptyVisited(boolean[] visited) {
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    private static void dfs(HashMap<Integer, LinkedList<Integer>> map, int position, LinkedList<ArrayList<Integer>> list, int i1) {
        if (!visited[position]) {
            if (list.size() <= i1) {
                if (list.size() == 0) list.add(new ArrayList<>());
                list.add(new ArrayList<>());
            }
            list.get(i1).add(position);
        }
        visited[position] = true;
        if (position > nextPosition) nextPosition = position;
        LinkedList<Integer> s = map.get(position);
        if (s == null) return;

        s.sort(Integer::compare);

        while (!s.isEmpty()) {
            int element = s.poll();
            if (visited[element]) continue;
            visited[element] = true;
            list.get(i1).add(element);
            dfs(map, element, list, i1);
        }
    }

    private static int findClosestPosition(int i, int vertexes, HashMap<Integer, LinkedList<Integer>> adjacentList) {
        if (i > vertexes) i = 1;
        while (adjacentList.get(i) != null && adjacentList.get(i).isEmpty()) {
            i += 1;
            if (adjacentList.get(i) == null) {
                nextPosition = i;
                return i;
            }
            if (i == vertexes) {
                for (int j = 1; j < visited.length; j++) {
                    if (!visited[j]) return j;
                }
            }
        }

        return i;
    }
}
