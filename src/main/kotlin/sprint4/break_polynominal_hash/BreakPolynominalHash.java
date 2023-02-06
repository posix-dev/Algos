package sprint4.break_polynominal_hash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BreakPolynominalHash {
    public static void main(String[] args) throws IOException {
        int q = 1000;
        int module = 123_987_123;
        HashMap<Long, String> map = new HashMap<>();
        ArrayList<Integer> intLetters = new ArrayList<>();
        ArrayList<Character> letters = new ArrayList<>();

        for (int i = 'a'; i < 123; i++) {
            intLetters.add(i);
        }
        for (int i = 'a'; i < 123; i++) {
            letters.add((char) i);
        }

        while (true) {
            String str = generateRandomString(letters);
            long hash = calculateHorner(str, q, module);

            if (map.get(hash) == null) {
                map.put(hash, str);
            } else {
                System.out.println(hash + " " + str);
                break;
            }
        }
    }

    private static String generateRandomString(ArrayList<Character> letters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int random = (int) (Math.random() * (letters.size()));
            builder.append(letters.get(random));
        }
        System.out.print(" " + builder);
        System.out.println();

        return builder.toString();
    }

    private static long calculateHorner(String str, int q, int module) {
        if (str.isEmpty()) return 0;

        long result = str.toCharArray()[0];

        for (int i = 1; i < str.length(); i++) {
            result = (result * q + str.toCharArray()[i]) % module;
        }

        return result;
    }
}
