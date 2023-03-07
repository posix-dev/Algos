package sprint7.difficult_flowers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class DifficultFlowers {
    static int[][] dp;
    static int[][] pathDp;

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

        printMaxFlowersValueAndPath(fields, rows, columns);
    }

    private static void printMaxFlowersValueAndPath(int[][] fields, int rows, int columns) {
//         rotate array
        for (int i = 0; i < fields.length / 2; i++) {
            int[] tmp = fields[i];
            fields[i] = fields[fields.length - i - 1];
            fields[fields.length - i - 1] = tmp;
        }

        dp = new int[rows + 1][columns + 1];
        pathDp = new int[rows + 1][columns + 1];
        dp[0][0] = fields[0][0];

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < columns + 1; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + fields[i - 1][j - 1];
                    pathDp[i][j] = dp[i - 1][j] + fields[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] + fields[i - 1][j - 1];
                    pathDp[i][j] = dp[i][j - 1] + fields[i - 1][j - 1];
                }
            }
        }

        int i = rows;
        int j = columns;
        boolean isZeros = false;
        Stack<String> list = new Stack<>();
        while (pathDp[i - 1][j] != 0 || pathDp[i][j - 1] != 0) {
            if (dp[i - 1][j] > dp[i][j - 1]) {
                list.push("U");
                i--;
            } else {
                list.push("R");
                j--;
            }
            isZeros = pathDp[i - 1][j] == 0 && pathDp[i][j - 1] == 0 && (i != 1 || j != 1);
        }

        if (isZeros) {
            while (j > 1 && i > 1) {
                if (fields[i - 1][j] > fields[i][j - 1]) {
                    list.push("U");
                    i--;
                } else {
                    j--;
                    list.push("R");
                }
            }
            while (j > 1) {
                j--;
                list.push("R");
            }
            while (i > 1) {
                i--;
                list.push("U");
            }
        }

        System.out.println(dp[rows][columns]);
        while (!list.isEmpty()) System.out.print(list.pop());
    }
}
