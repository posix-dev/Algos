package sprint2.recursion_fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecursionFibonacci {
    public static void main(String[] args) throws IOException {
        int n;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
        }

        // n нумеруется с 0, поэтому и + 1
        System.out.println(getFibonacci(n + 1));
    }

    private static int getFibonacci(int n) {
        if (n < 2)  return n;

        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }
}
