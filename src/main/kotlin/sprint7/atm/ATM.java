package sprint7.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM {

    public static int finish = 0;

    public static void main(String[] args) throws IOException {
        int finalSum, length;
        StringTokenizer tokenizer;
        int[] atmPrices;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            finalSum = Integer.parseInt(reader.readLine());
            length = Integer.parseInt(reader.readLine());

            atmPrices = new int[length];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < length; i++) {
                int price = Integer.parseInt(tokenizer.nextToken());
                atmPrices[i] = price;
            }
        }
        Arrays.sort(atmPrices);

        getPossibleWays(atmPrices, 0, finalSum);
        System.out.println(finish);
    }

    private static void getPossibleWays(int[] atmPrices, int index, int reducedNum) {
        if (reducedNum < 0) {
            return;
        } else if (reducedNum == 0) {
            ++finish;
            return;
        }

        for (int k = index; k < atmPrices.length; k++) {
            getPossibleWays(atmPrices, index, reducedNum - atmPrices[k]);
            ++index;
        }
    }
}
