package sprint7.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Knapsack {
    public static int N;
    public static int M;
    public static int[] weight;
    public static int[] profit;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken()); // max weight

            weight = new int[N + 1];
            if (N > M) {
                profit = new int[N + 1];
            } else {
                profit = new int[M + 1];
            }

            dp = new int[weight.length][profit.length];

            for (int i = 1; i < N + 1; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                weight[i] = Integer.parseInt(tokenizer.nextToken());
                profit[i] = Integer.parseInt(tokenizer.nextToken());
            }

            getKnapsackCount();
            getStacktrace(N, M, new ArrayList<>());
        }
    }

    private static void getStacktrace(int i, int j, ArrayList<Integer> list) {
        if (dp[i][j] == 0) {
            System.out.println(list.size());
            for (int value : list) {
                System.out.print(value + " ");
            }
            return;
        }

        if (dp[i][j] == dp[i - 1][j]) {
            getStacktrace(i - 1, j, list);
        } else {
            list.add(i);
            getStacktrace(i - 1, j - weight[i], list);
        }
    }

    private static void getKnapsackCount() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (j - weight[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], profit[i] + dp[i - 1][j - weight[i]]);
                }
            }
        }
    }
}
