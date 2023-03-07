package sprint7.horoscopes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Horoscopes {
    static int[][] dp;

    public static void main(String[] main) throws IOException {
        int length, secondLength;
        int[] firstArr;
        int[] secondArr;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            firstArr = new int[length];

            int i = 0;
            while (tokenizer.hasMoreTokens()) {
                int value = Integer.parseInt(tokenizer.nextToken());

                firstArr[i] = value;
                i++;
            }

            i = 0;
            secondLength = Integer.parseInt(reader.readLine());
            secondArr = new int[secondLength];
            tokenizer = new StringTokenizer(reader.readLine());

            while (tokenizer.hasMoreTokens()) {
                int value = Integer.parseInt(tokenizer.nextToken());

                secondArr[i] = value;
                i++;
            }
            dp = new int[length + 1][secondLength + 1];

            findBiggestCommonPart(firstArr, secondArr);
            findPath(firstArr, secondArr);
        }
    }

    private static void findPath(int[] firstArr, int[] secondArr) {
        int i = firstArr.length - 1;
        int j = secondArr.length - 1;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        while (dp[i][j] != 0 || (i > 0 && j > 0)) {
            if (firstArr[i] == secondArr[j]) {
                list.add(i);
                list2.add(j);
                i--;
                j--;
            } else {
                if (dp[i][j] == dp[i - 1][j]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        System.out.println();
    }

    private static void findBiggestCommonPart(int[] A, int[] B) {
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[A.length][B.length]);
    }

}
