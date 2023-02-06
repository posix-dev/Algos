package sprint4.stange_compare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class StrangeCompare {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            char[] str = reader.readLine().toCharArray();
            char[] str2 = reader.readLine().toCharArray();

            if (str.length != str2.length) {
                System.out.println("NO");
                return;
            }

            HashMap<Character, Character> map = new HashMap<>();

            char previousChar1 = ')';
            char previousChar2 = '(';
            for (int i = 0; i < str.length; i++) {
                char c1 = str[i];
                char c2 = str2[i];

                if(!(c1 == previousChar1 && c2 == previousChar2)) {
                    if (previousChar1 != c1 && previousChar2 != c2) {
                        map.put(previousChar1, previousChar2);
                    } else {
                        System.out.println("NO");
                        return;
                    }
                }

                previousChar1 = c1;
                previousChar2 = c2;
            }

            map.put(previousChar1, previousChar2);

            StringBuilder builder = new StringBuilder();
            for (char c : str) {
                builder.append(map.get(c));
            }

            if (builder.toString().equals(String.valueOf(str2))) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
