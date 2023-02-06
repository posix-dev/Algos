package sprint4.substring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestUniqueSubstring {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            System.out.println(findLongestStr(str));
        }
    }

    private static int findLongestStr(String str) {
        int biggestResult = 0;

        for (int i = 0; i < str.length(); i++) {
            boolean[] letters = new boolean[256];
            int result = 0;
            for (int j = i; j < str.length(); j++) {
                if (letters[str.charAt(j)]) break;

                letters[str.charAt(j)] = true;
                result++;
                biggestResult = Math.max(biggestResult, result);
            }
        }

        return biggestResult;
    }
}
