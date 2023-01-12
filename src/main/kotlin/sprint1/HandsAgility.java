package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

// Id = 80180669
// Link: https://contest.yandex.ru/contest/22450/run-report/80180669/

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
        int result = 0;
        String stringWithOnlyNumbers = filterFromCharacters(builder.toString());

        if (!stringWithOnlyNumbers.isEmpty()) {
            String[] strArray = stringWithOnlyNumbers.split("");
            Arrays.sort(strArray, Comparator.comparingInt(Integer::parseInt));

            String temporaryNumber;
            int count;
            for (int i = 0; i < strArray.length; i++) {
                count = 1;
                temporaryNumber = strArray[i];

                while (i + 1 != strArray.length && Objects.equals(temporaryNumber, strArray[i + 1])) {
                    count += 1;
                    i++;
                }

                if (count <= k * PLAYERS) {
                    result += 1;
                }
            }
        }

        System.out.println(result);
    }

    private static String filterFromCharacters(String str) {
        char[] arr = str.toCharArray();
        StringBuilder resultStr = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                resultStr.append(arr[i]);
            }
        }

        return resultStr.toString();
    }

}

