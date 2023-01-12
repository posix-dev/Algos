package sprint2.fibonacci_module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FibonacciModule {
    public static void main(String[] args) throws IOException {
        int n;
        int moduloPow;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            moduloPow = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println(getFibonacciWithModulo(n + 1, moduloPow));
    }

    private static long getFibonacciWithModulo(int n, int pow) {
        if (n < 2) return n;

        int module = (int) Math.pow(10, pow);

        long prev = 0;
        long curr = 1;

        for (int i = 0; i < n - 1; i++) {
            long temp;
            temp = curr;
            curr = (prev + curr) % module;
            prev = temp;
        }

        return curr % module;
    }
}
