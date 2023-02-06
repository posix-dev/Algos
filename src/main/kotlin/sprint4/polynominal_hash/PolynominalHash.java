package sprint4.polynominal_hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PolynominalHash {
    public static void main(String[] args) throws IOException {
        int q, module;
        ArrayList<Integer> list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            q = Integer.parseInt(reader.readLine());
            module = Integer.parseInt(reader.readLine());
            char[] arr = reader.readLine().toCharArray();
            list = new ArrayList<>(arr.length);
            for (char c : arr) {
                list.add((int) c);
            }

            System.out.println(calculateHorner(list, q, module));
        }
    }

    private static long calculateHorner(ArrayList<Integer> list, int q, int module) {
        if (list.isEmpty()) return 0;

        long result = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            result = (result * q + list.get(i)) % module;
        }

        return result;
    }
}
