package sprint4.prefix_hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PrefixHash {
    public static void main(String[] args) throws IOException {
        int q, module, positionLength;
        ArrayList<Integer> list;
        HashMap<String, Long> map = new HashMap();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            q = Integer.parseInt(reader.readLine());
            module = Integer.parseInt(reader.readLine());
            char[] arr = reader.readLine().toCharArray();
            list = new ArrayList<>(arr.length);
            positionLength = Integer.parseInt(reader.readLine());
            for (char c : arr) {
                list.add((int) c);
            }

            for (int i = 0; i < positionLength; i++) {
                System.out.println(
                        calculateHorner(
                                list, q, module, reader.readLine().split(" "), map
                        )
                );
            }
        }
    }

    private static long calculateHorner(ArrayList<Integer> list, int q, int module, String[] iterations, HashMap<String, Long> map) {
        if (list.isEmpty()) return 0;

        int start = Integer.parseInt(iterations[0]) - 1;
        int end = Integer.parseInt(iterations[1]) - 1;

        if (start == end) return list.get(start) % module;
        long result = list.get(start);

        for (int i = start; i < end; i++) {
            result = (result * q + list.get(i + 1)) % module;
        }

        return result;
    }
}
