package sprint7.homework.mine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LEVMine {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        char[] str1, str2;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine().toCharArray();
            str2 = reader.readLine().toCharArray();
            dp = new int[str1.length + 1][str2.length + 1];

            System.out.println(getLEVDistance(str1, str2));
        }
    }

    private static int getLEVDistance(char[] str1, char[] str2) {
        for (int i = 0; i <= str1.length; i++) {
            for (int j = 0; j <= str2.length; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = getMinDistance(
                            dp[i - 1][j - 1] + getReplacementDiff(str1[i - 1], str2[j - 1]), //replacement
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1 // insert
                    );
                }
            }
        }

        return dp[str1.length][str2.length];
    }

    static int getReplacementDiff(char firstCh, char secondCh) {
        return firstCh == secondCh ? 0 : 1;
    }

    private static int getMinDistance(int... num) {
        return Arrays.stream(num).min().orElse(Integer.MAX_VALUE);
    }

}
