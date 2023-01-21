package sprint3.subsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Subsequence {
    public static void main(String[] args) throws IOException {
        String subsequence;
        String strArray;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            subsequence = reader.readLine();
            strArray = reader.readLine();
        }

        if (isSubSequence(subsequence, strArray, subsequence.length(), strArray.length())) {
            System.out.print("True");
        } else {
            System.out.print("False");
        }
    }

    static boolean isSubSequence(String str1, String str2, int length1, int length2) {
        // Base Cases
        if (length1 == 0) return true;
        if (length2 == 0) return false;

        // If last characters of two strings are matching
        if (str1.charAt(length1 - 1) == str2.charAt(length2 - 1))
            return isSubSequence(str1, str2, length1 - 1, length2 - 1);

        // If last characters are not matching
        return isSubSequence(str1, str2, length1, length2 - 1);
    }
}
