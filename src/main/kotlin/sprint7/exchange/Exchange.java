package sprint7.exchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exchange {
    public static void main(String[] args) throws IOException {
        int length;
        StringTokenizer tokenizer;
        int[] daysPrice;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());

            daysPrice = new int[length];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < length; i++) {
                int price = Integer.parseInt(tokenizer.nextToken());
                daysPrice[i] = price;
            }
        }

        System.out.println(getMaxProfit(daysPrice));
    }

    private static int getMaxProfit(int[] daysPrice) {
        if (isSorted(daysPrice)) {
            return daysPrice[daysPrice.length - 1] - daysPrice[0];
        }

        int result = 0;
        int currentPrice = Integer.MAX_VALUE;
        for (int i = 0; i < daysPrice.length; i++) {
            if (daysPrice[i] < currentPrice) {
                currentPrice = daysPrice[i];
            } else {
                if (i + 1 == daysPrice.length) {
                    if (daysPrice[i - 1] < daysPrice[i]) {
                        result += daysPrice[i] - daysPrice[i - 1];
                    }
                } else {
                    if (daysPrice[i] > currentPrice && daysPrice[i] > daysPrice[i + 1]) {
                        result += daysPrice[i] - currentPrice;
                    } else if (currentPrice < daysPrice[i + 1]) {
                        result += daysPrice[i + 1] - currentPrice;
                    }
                }
                currentPrice = Integer.MAX_VALUE;
            }
        }

        return result;
    }

    private static boolean isSorted(int[] daysPrice) {
        if (daysPrice == null || daysPrice.length == 0) return true;

        int prevValue = daysPrice[0];
        for (int i = 1; i < daysPrice.length; i++) {
            if (daysPrice[i] < prevValue) return false;
            prevValue = daysPrice[i];
        }

        return true;
    }
}
