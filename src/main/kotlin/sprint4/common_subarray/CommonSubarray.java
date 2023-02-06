package sprint4.common_subarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CommonSubarray {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int firstLength = Integer.parseInt(reader.readLine());
            String str = reader.readLine();
            int secondLength = Integer.parseInt(reader.readLine());
            String str2 = reader.readLine();

            String[] shortString;
            String longString;

            if (str.length() > str2.length()) {
                shortString = str2.split(" ");
                longString = str;
            } else {
                shortString = str.split(" ");
                longString = str2;
            }

            HashMap<String, Integer> substrings = new HashMap<>(shortString.length * 2 - 1);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < shortString.length; i++) {
                if (i == shortString.length - 1) {
                    builder.append(shortString[i]);
                } else {
                    builder.append(shortString[i]).append(" ");
                }
                substrings.put(builder.toString().trim(), 0);
                substrings.put(shortString[i], 0);
            }

            builder.setLength(0);
            for (int i = shortString.length - 1; i > -1; i--) {
                String item = shortString[i];
                builder.insert(0, item + " ");
                substrings.put(builder.toString().trim(), 0);
            }

            int finish = -1;
            for (String substring : substrings.keySet()) {
                if (longString.contains(substring)) {
                    if (substring.split(" ").length > 1) {
                        int localIndex = longString.indexOf(substring);
                        if (localIndex + substring.length() < longString.length()) {
                            char checkStr = longString.charAt(localIndex + substring.length());
                            if (checkStr != ' ') continue;
                        }
                        if (localIndex - 1 > 0) {
                            char checkStr = longString.charAt(localIndex - 1);
                            if (checkStr != ' ') continue;
                        }
                    }
                    String[] arr = substring.split(" ");
                    if (finish < arr.length) {
                        finish = arr.length;
                    }
                }
            }

            if (finish != -1) {
                System.out.println(finish);
                return;
            }
            System.out.println(0);
        }
    }
}
