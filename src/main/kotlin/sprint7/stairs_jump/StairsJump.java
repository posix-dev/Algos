package sprint7.stairs_jump;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StairsJump {
    private static int[] dp;

    private static final int MODULE = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        int maxJump, length;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            length = Integer.parseInt(tokenizer.nextToken());
            maxJump = Integer.parseInt(tokenizer.nextToken());

            dp = new int[length + 1];

            System.out.println(calculateLeaps(length, maxJump));
        }
    }

    static int calculateLeaps(int n, int maxJump) {
        if (n < 1) return 0;
        else if (n == 1) return 1;
        else if (dp[n] == 0) {
            for (int i = 1; i <= maxJump; i++) {
                dp[n] += calculateLeaps(n - i, maxJump);
                dp[n] %= MODULE;
            }
        }
        return dp[n] % MODULE;
    }
}
