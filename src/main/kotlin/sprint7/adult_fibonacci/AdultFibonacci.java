package sprint7.adult_fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdultFibonacci {
    public static void main(String[] args) throws IOException {
        int number;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            number = Integer.parseInt(reader.readLine());
            int module = (int) (Math.pow(10, 9) + 7);

            System.out.println(getFibonacciNumber(number, module));
        }
    }

    private static long getFibonacciNumber(int number, int module) {
        long a = 1, b = 1, c;

        for (int i = 2; i <= number; i++) {
            c = (a + b) % module;
            a = b % module;
            b = c % module;
        }

        return b;
    }
}
