import java.util.*;

// Id = 80108166
// Link: https://contest.yandex.ru/contest/22450/run-report/80108166/

public final class HandsAgility {
    private static final Integer PLAYERS = 2;

    public static void main(String[] args) {
        int k = 1;
        String builder = "1999" +
                "7778" +
                "3336" +
                "4445";
        int result = 0;
        String stringWithOnlyNumbers = filterFromCharacters(builder);

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
