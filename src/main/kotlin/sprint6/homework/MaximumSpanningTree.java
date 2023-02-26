package sprint6.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал алгоритм максимального остового дерева.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98796/topics/45179065-a73b-473d-94d1-24774573f266/lessons/adb9a06e-f8a5-4d9b-b88a-2085cc8458f9/
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98796/topics/45179065-a73b-473d-94d1-24774573f266/lessons/e895a178-7bd3-4549-95a1-c445f8413e83/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Предисловие:
 * Для начала реализовал класс данных Edge с Comparable для удобства использования PriorityQueue в дальнейшем,
 * то есть 1-ый эл-т будет максимальным.
 * Так же при добавлении игнорирую добавление эл-та, который ссылается сам на себя и цепочки с повторяющимся ребром
 * меньшего веса.
 * Алгоритм:
 * Записываю данные в обе стороны, тк граф не ориентированный, инкрементируя переменную notAddedValues.
 * Далее если vertex - 1 > edges то делаю обход по алгоритма Прима для нахождения максимального остового дерева.
 * Добавляю изначально 1 эл-т в очередь(PriorityQueue) и захожу в цикл.
 * Алгоритм цикла:
 * Извзлекаю из начала(верха) эл-т, проверяю были ли мы здесь(если не были помечаю, что посетили эл-т),
 * инкрементирую переменную finalValue(это переменная ценности остова, которую ищем),
 * нахожу мапу innerMap по этому эл-ту. Далее добавляю все эл-ты innerMap в queue. Далее повтор.
 * <p>
 * В конце метода проверяю если мы прошли все добавленные изначально эл-ты(notAddedValues == -1, на самом деле
 * должно быть 0, но мы в последней итерации проходим лишний раз), то показываем finalValue.
 * <p>
 * Во всех остальных случаях мы ожидаем ошибку "Oops! I did it again".
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * E - кол-во рёбер;
 * V - кол-во вершин;
 * <p>
 * В данном алгоритме я затрачу O(E) на формирование изначального списка рёбер и дальнейший алгоритм займет
 * O(E * logV).
 * P.S. тк мы проходим цикл по ребрам(E) и добавляя вершину затрачиваем logV. Извлечение так же logV.
 * = O(E * 2logV) = O(E * logV).
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Данный алгоритм будет занимать O(E) памяти списка рёбер + массив visited O(V) памяти = O(V + E);
 */

// Id: 82742541
// Link: https://contest.yandex.ru/contest/25070/run-report/82742541/

public class MaximumSpanningTree {
    static Graph graph;

    public static void main(String[] args) throws IOException {
        int edges, vertexes;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            graph = new Graph(vertexes);
            edges = Integer.parseInt(tokenizer.nextToken());
            HashMap<Integer, Integer> edgeList;
            int vertex, secondVertex, value;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                secondVertex = Integer.parseInt(tokenizer.nextToken());
                value = Integer.parseInt(tokenizer.nextToken());

                if (vertex == secondVertex) continue;

                if (graph.containsVertex(vertex)) {
                    edgeList = graph.getEdgeList(vertex);
                } else {
                    edgeList = new HashMap<>();
                }
                graph.putEdgeList(vertex, edgeList);

                if (edgeList.containsKey(secondVertex)) {
                    if (edgeList.get(secondVertex) < value) edgeList.put(secondVertex, value);
                } else {
                    graph.incrementNotAddedValue();
                    edgeList.put(secondVertex, value);
                }

                if (graph.containsVertex(secondVertex)) {
                    edgeList = graph.getEdgeList(secondVertex);
                } else {
                    edgeList = new HashMap<>();
                }

                if (edgeList.containsKey(vertex)) {
                    if (edgeList.get(vertex) < value) edgeList.put(vertex, value);
                } else {
                    graph.incrementNotAddedValue();
                    edgeList.put(vertex, value);
                }

                graph.putEdgeList(secondVertex, edgeList);
            }

            if (vertexes - 1 > edges) {
                System.out.println("Oops! I did it again");
            } else {
                getMaxSpanningTree();
            }
        }
    }

    private static void getMaxSpanningTree() {
        int startNumber = 1;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(startNumber, 0, -1));
        Edge edge;
        int vertex, parentVertex, cost;

        while (!queue.isEmpty()) {
            edge = queue.remove();

            graph.decrementNotAddedValue();

            if (graph.isVisited(edge.getVertex() - 1)) continue;
            graph.setVisitedVertex(edge.getVertex() - 1, true);

            graph.incrementFinalValue(edge.getCost());

            HashMap<Integer, Integer> vList = graph.getEdgeList(edge.getVertex());
            if (vList == null) continue;

            for (Map.Entry<Integer, Integer> value : vList.entrySet()) {
                vertex = value.getKey();
                cost = value.getValue();
                parentVertex = edge.getVertex();
                queue.add(new Edge(vertex, cost, parentVertex));
            }
        }

        if (graph.isMaxSpanningTree()) {
            System.out.println("Oops! I did it again");
        } else {
            System.out.println(graph.getFinalValue());
        }
    }
}


class Edge implements Comparable<Edge> {

    int parentVertex;

    int vertex;

    int cost;

    public Edge(int vertex, int cost, int parentVertex) {
        this.parentVertex = parentVertex;
        this.vertex = vertex;
        this.cost = cost;
    }

    public int getVertex() {
        return vertex;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(o.cost, this.cost);
    }
}

class Graph {
    boolean[] visited;

    int finalValue = 0;

    int notAddedValues = 0;

    HashMap<Integer, HashMap<Integer, Integer>> adjacents;

    Graph(int vertexes) {
        visited = new boolean[vertexes];
        adjacents = new HashMap<>();
    }

    public void setVisitedVertex(int index, boolean value) {
        visited[index] = value;
    }

    public boolean isVisited(int index) {
        return visited[index];
    }


    public int getFinalValue() {
        return finalValue;
    }

    public void incrementFinalValue(int value) {
        this.finalValue += value;
    }

    public int getNotAddedValues() {
        return notAddedValues;
    }

    public void incrementNotAddedValue() {
        this.notAddedValues += 1;
    }

    public void decrementNotAddedValue() {
        this.notAddedValues -= 1;
    }

    public boolean isMaxSpanningTree() {
        return getNotAddedValues() > -1;
    }

    public boolean containsVertex(int vertex) {
        return adjacents.containsKey(vertex);
    }

    public HashMap<Integer, Integer> getEdgeList(int vertex) {
        return adjacents.get(vertex);
    }

    public void putEdgeList(int vertex, HashMap<Integer, Integer> map) {
        adjacents.put(vertex, map);
    }

}