package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// Id = 80108166
// Link: https://contest.yandex.ru/contest/22450/run-report/80108166/

public final class HandsAgility {
    private static final Integer PLAYERS = 2;
    private static final Integer ROUNDS = 9;

    public static void main(String[] args) throws IOException {
        int k;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            k = Integer.parseInt(reader.readLine().strip());
            builder.append(reader.readLine().strip());
            builder.append(reader.readLine().strip());
            builder.append(reader.readLine().strip());
            builder.append(reader.readLine().strip());
        }
        char[] charArray = builder.toString().toCharArray();
        HashMap<Character, Integer> mapWithRepeatNumbers = getMapWithRepeatNumbers(charArray);

        int result = getResult(mapWithRepeatNumbers, k);

        System.out.println(result);
    }

    private static HashMap<Character, Integer> getMapWithRepeatNumbers(char[] charArray) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : charArray) {
            if (Character.isDigit(c)) {
                map.put(c, (map.getOrDefault(c, 0)) + 1);
            }
        }

        return map;
    }

    private static int getResult(HashMap<Character, Integer> mapWithRepeatNumbers, int k) {
        int result = 0;

        for (int t = 1; t <= ROUNDS; t++) {
            char charValue = (char) (t + '0');
            if (mapWithRepeatNumbers.get(charValue) != null && mapWithRepeatNumbers.get(charValue) <= k * PLAYERS) {
                result += 1;
            }
        }

        return result;
    }
}
