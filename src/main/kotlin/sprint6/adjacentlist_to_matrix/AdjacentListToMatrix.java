package sprint6.adjacentlist_to_matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AdjacentListToMatrix {
    public static void main(String[] args) throws IOException {
        int edges, vertexes;
        int[][] matrix;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            vertexes = Integer.parseInt(tokenizer.nextToken());
            edges = Integer.parseInt(tokenizer.nextToken());
            matrix = new int[vertexes][vertexes];
            int vertex, edge;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                vertex = Integer.parseInt(tokenizer.nextToken());
                edge = Integer.parseInt(tokenizer.nextToken());

                matrix[vertex - 1][edge - 1] = 1;
            }

            for (int[] ints : matrix) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println();
            }
        }
    }
}
