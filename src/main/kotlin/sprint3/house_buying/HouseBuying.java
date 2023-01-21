package sprint3.house_buying;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HouseBuying {
    public static void main(String[] args) throws IOException {
        int houses, budget;
        int result = 0;
        int[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String firstLine = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(firstLine);
            houses = Integer.parseInt(tokenizer.nextToken());
            budget = Integer.parseInt(tokenizer.nextToken());
            String secondStr = reader.readLine();
            StringTokenizer tokenizer2 = new StringTokenizer(secondStr);
            list = new int[tokenizer2.countTokens()];
            int index = 0;
            while (tokenizer2.hasMoreTokens()) {
                list[index] = Integer.parseInt(tokenizer2.nextToken());
                index++;
            }
            Arrays.sort(list);
        }

        for (int i = 0; i < houses; i++) {
            int sum = result + list[i];

            if (sum > budget) {
                System.out.println(i);
                return;
            } else {
               result = sum;
            }
        }

        System.out.println(houses);
    }
}
