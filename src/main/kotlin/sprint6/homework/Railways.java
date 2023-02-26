package sprint6.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал алгоритм понимания оптимальный маршрут или нет.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98796/topics/45179065-a73b-473d-94d1-24774573f266/lessons/13789dd6-3b8b-4d85-92bb-4c256edc79c5/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Изначально заполняю данный алгоритм данными. Если встречаю символ 'B' записываю номер строки [i;i+j],
 * если же R [i + j, i], то есть разворачиваю ребро. Далее по циклу dfs ищу цикл (0 = белый, 1 = серый, 2 = черный)
 * Если эл-т белый(то есть мы туда даже не заходили) красим в серый и снова добавляем его в стэк(то есть этот эл-т и может себя же закрыть)
 * и далее проходим по всем эл-ам к которым он ведёт, добавляем все эти точки в стэк, в конечном счёте если при проходе
 * точек в глубину мы попадаем на серый цвет, то мы попали на цикл, то есть маршрут не оптимален.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * * E - кол-во рёбер;
 * * V - кол-во вершин;
 * <p>
 * В данном алгоритме я использую списки смежности, т.е. в среднем получится O(V + E);
 * а в худшем случае(полный граф) O(V^2)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Данный алгоритм будет занимать O(V + E) памяти + массив colors O(V) памяти = O(2V + E) = O(V + E);
 */

// Id: 82789370
// Link: https://contest.yandex.ru/contest/25070/run-report/82789370/

public class Railways {

    static int capital;
    static RailwayGraph graph;

    public static void main(String[] args) throws IOException {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            capital = Integer.parseInt(tokenizer.nextToken());

            graph = new RailwayGraph(capital);

            for (int i = 1; i < capital; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();

                for (int j = 0; j < line.length(); j++) {
                    graph.setVertex(line.charAt(j), i, j + 1);
                }
            }
        }

        if (graph.isOptimized()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

class RailwayGraph {
    List<Integer>[] vertexes;
    Color[] colors;

    RailwayGraph(int size) {
        vertexes = new List[size + 1];
        colors = new Color[size + 1];
        Arrays.fill(colors, Color.WHITE);
    }

    public List<Integer> getVertex(int index) {
        return vertexes[index];
    }

    public void setVertex(char c, int from, int to) {
        if (c == 'B') {
            if (getVertex(from) == null) vertexes[from] = new ArrayList<>();
            vertexes[from].add(from + to);
        } else {
            if (vertexes[from + to] == null) vertexes[from + to] = new ArrayList<>();
            vertexes[from + to].add(from);
        }
    }

    public List<Integer>[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Integer>[] vertexes) {
        this.vertexes = vertexes;
    }

    public boolean isOptimized() {
        return vertexes.length - 1 == 1 || !isNotOptimized();
    }

    private boolean isNotOptimized() {
        for (int i = 1; i < colors.length - 1; i++) {
            if (colors[i] == Color.WHITE) {
                if (isCycled(i)) return true;
            }
        }

        return false;
    }

    private boolean isCycled(int position) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list;
        stack.push(position);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (colors[current] == Color.WHITE) {
                colors[current] = Color.GRAY;
                stack.push(current);

                list = vertexes[current];
                if (list == null) continue;

                for (int value : list) {
                    if (colors[value] == Color.WHITE) {
                        stack.push(value);
                    } else if (colors[value] == Color.GRAY) {
                        return true;
                    }
                }
            } else if (colors[current] == Color.GRAY) {
                colors[current] = Color.BLACK;
            }
        }

        return false;
    }
}


enum Color {
    WHITE, GRAY, BLACK
}