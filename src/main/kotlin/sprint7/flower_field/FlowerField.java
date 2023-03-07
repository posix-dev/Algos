package sprint7.flower_field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlowerField {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int rows, columns;
        StringTokenizer tokenizer;
        int[][] fields;


        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            tokenizer = new StringTokenizer(reader.readLine());
            rows = Integer.parseInt(tokenizer.nextToken());
            columns = Integer.parseInt(tokenizer.nextToken());
            fields = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();
                for (int j = 0; j < columns; j++) {
                    fields[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }
        }

        System.out.println(getMaxFlowers(fields, rows, columns));
    }

    private static int getMaxFlowers(int[][] fields, int rows, int columns) {
//         rotate array
        for (int i = 0; i < fields.length / 2; i++) {
            int[] tmp = fields[i];
            fields[i] = fields[fields.length - i - 1];
            fields[fields.length - i - 1] = tmp;
        }

        dp = new int[rows + 1][columns + 1];
        dp[0][0] = fields[0][0];

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < columns + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + fields[i - 1][j - 1];
            }
        }

        return dp[rows][columns];
    }
}
