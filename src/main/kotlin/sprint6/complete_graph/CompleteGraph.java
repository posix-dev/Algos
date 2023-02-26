package sprint6.complete_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CompleteGraph {
    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        int[][] matrix;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            matrix = new int[vertexes][vertexes];

            for (int[] values : matrix) Arrays.fill(values, -1);

            edges = Integer.parseInt(tokenizer.nextToken());
            int vertex, edge;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                edge = Integer.parseInt(tokenizer.nextToken());

                if (vertex == edge) continue;

                matrix[vertex - 1][edge - 1] = 1;
                matrix[edge - 1][vertex - 1] = 1;
            }

            for (int i = 0; i < vertexes; i++) matrix[i][i] = 0;

            if (isFull(matrix)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isFull(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == -1 || matrix[j][i] == -1) return false;
            }
        }
        return true;
    }
}
