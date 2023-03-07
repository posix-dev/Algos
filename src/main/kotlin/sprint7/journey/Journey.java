package sprint7.journey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Journey {
    static int[] dp;
    static int[] pathDp;

    public static void main(String[] args) throws IOException {
        int length;
        ArrayList<Integer> journeyPoints;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            journeyPoints = new ArrayList<>(length);
            dp = new int[length];
            pathDp = new int[length];

            tokenizer = new StringTokenizer(reader.readLine());
            int element;
            while (tokenizer.hasMoreTokens()) {
                element = Integer.parseInt(tokenizer.nextToken());

                journeyPoints.add(element);
            }

            findMaxJourney(journeyPoints);
        }
    }

    private static void findMaxJourney(ArrayList<Integer> journeyPoints) {
        dp[0] = 0;
        pathDp[0] = -1;
        int lastJ = 0;

        for (int i = 1; i < journeyPoints.size(); i++) {
            dp[i] = 0;
            pathDp[i] = -1;

            for (int j = 0; j < i; j++) {
                if (journeyPoints.get(j) < journeyPoints.get(i)) {
                    if (dp[i] == 0) dp[i] = dp[lastJ] + 1;
                    if (pathDp[i] == -1) pathDp[i] = j;
                    lastJ = i;
                }
            }
        }

        System.out.println(dp[dp.length - 1] + 1);
        int indexValue = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pathDp.length; i++) {
            if (pathDp[i] != -1) {
                ++indexValue;
                builder.append(i + 1).append(" ");
//                System.out.print(i + 1 + " ");
            }
        }
        if (indexValue < dp[dp.length - 1] + 1) {
            builder.insert(0, 1).insert(1, " ");
        }
        System.out.println(builder);
    }
}
