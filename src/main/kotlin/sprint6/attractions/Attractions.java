package sprint6.attractions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Attractions {

    static boolean[][] visited;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        // key is from where value is map of key to where and value
        HashMap<Integer, HashMap<Integer, Integer>> adjacents;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            adjacents = new HashMap<>();
            edges = Integer.parseInt(tokenizer.nextToken());
            int vertex, edge, value;
            HashMap<Integer, Integer> subMap;
            matrix = new int[vertexes][vertexes];
            visited = new boolean[vertexes][vertexes];

            for (int[] values : matrix) Arrays.fill(values, Integer.MAX_VALUE);
            for (int i = 0; i < vertexes; i++) {
                matrix[i][i] = 0;
                visited[i][i] = true;
            }

            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                edge = Integer.parseInt(tokenizer.nextToken());
                value = Integer.parseInt(tokenizer.nextToken());

                if (adjacents.containsKey(vertex)) {
                    subMap = adjacents.get(vertex);
                } else {
                    subMap = new HashMap<>();
                }

                subMap.put(edge, value);
                adjacents.put(vertex, subMap);

                if (adjacents.containsKey(edge)) {
                    subMap = adjacents.get(edge);
                } else {
                    subMap = new HashMap<>();
                }

                subMap.put(vertex, value);
                adjacents.put(edge, subMap);

                if (matrix[vertex - 1][edge - 1] == Integer.MAX_VALUE || matrix[vertex - 1][edge - 1] > value) {
                    matrix[vertex - 1][edge - 1] = value;
                    matrix[edge - 1][vertex - 1] = value;
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (!visited[i][j]) {
                        int innerI = Math.min(i, j);
                        int innerJ = Math.max(i, j);
                        int sum = sumRestPoints(innerI + 1, innerJ + 1, matrix, adjacents);
                        if (matrix[i][j] > sum) {
                            matrix[i][j] = sum;
                            matrix[j][i] = sum;
                        }
                        visited[i][j] = true;
                        visited[j][i] = true;
                    }
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static int sumRestPoints(int i, int j, int[][] matrix, HashMap<Integer, HashMap<Integer, Integer>> map) {
        PriorityQueue<Node222> list = new PriorityQueue<>();
        ArrayList<Node222> res = new ArrayList<>();
        list.add(new Node222(0, i, -1));
        boolean[] visited = new boolean[matrix.length];

        while (!list.isEmpty()) {
            Node222 element = list.poll();

            if (element.getNode() == j) {
                res.add(element);
                continue;
            }

            if (visited[element.getNode() - 1]) continue;
            visited[element.getNode() - 1] = true;

            HashMap<Integer, Integer> inner = map.get(element.getNode());
            if (inner == null) return -1;
            for (Map.Entry<Integer, Integer> value : inner.entrySet()) {
                if (element.getCost() != -1) {
                    list.add(new Node222(value.getValue() + element.getCost(), value.getKey(), element.getNode()));
                } else {
                    list.add(new Node222(value.getValue(), value.getKey(), element.getNode()));
                }
            }
        }

        if (res.isEmpty()) return -1;
        return res.remove(0).getCost();
    }

}

class Node222 implements Comparable<Node222> {
    private int cost;

    private int node;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getParentNode() {
        return parentNode;
    }

    public void setParentNode(int parentNode) {
        this.parentNode = parentNode;
    }

    private int parentNode;

    public Node222(int cost, int node, int parentNode) {
        this.cost = cost;
        this.node = node;
        this.parentNode = parentNode;
    }

    @Override
    public int compareTo(Node222 o) {
        return Integer.compare(this.cost, o.cost);
    }
}
