package sprint7.alla_on_algos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AllaOnAlgos {
    public static void main(String[] args) throws IOException {
        int sum, length;
        Integer[] money;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            sum = Integer.parseInt(reader.readLine());
            length = Integer.parseInt(reader.readLine());
            money = new Integer[length];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < length; i++) {
                money[i] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(money, (o1, o2) -> -Integer.compare(o1, o2));

            int position = -1;
            for (int i = 0; i < money.length; i++) {
                if (sum % money[i] == 0) {
                    position = i;
                    break;
                }
            }

            System.out.println(getCurrencyCount(sum, money, position));
        }
    }

    public static int getCurrencyCount(int sum, Integer[] money, int position) {
        int result = 0;
        int temporaryRes = 0;
        int i = 0;
        int j = 0;
        int tempJ = 0;
        int temp = money[position];
        while (result != sum) {
            int element = money[i];

            while (result == 0 || result % temp != 0 && result + element < sum) {
                result += element;
                temporaryRes = result;
                j += 1;
                tempJ = j;
            }

            while (result + temporaryRes < sum) {
                result += temporaryRes;
                j += tempJ;
            }

            while (result + temp > sum && i < money.length) {
                temp = money[i];
                ++i;
            }

            while (sum != result) {
                j += 1;
                result += temp;
            }
        }

        return j;
    }

}
