package sprint4.four_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class FourSum {
    public static void main(String[] args) throws IOException {
        int length;
        long sum;
        long[] arr;
        LinkedHashSet<String> set = new LinkedHashSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            sum = Integer.parseInt(reader.readLine());
            arr = new long[length];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int index = 0;
            while (tokenizer.hasMoreElements()) {
                arr[index] = Integer.parseInt(tokenizer.nextToken());
                index++;
            }
            Arrays.sort(arr);

            fuckYou(arr, sum, set);

            System.out.println(set.size());
            for (String s : set) {
                System.out.println(s);
            }
        }
    }

    private static void fuckYou(long[] arr, long sum, LinkedHashSet<String> set) {
        if (arr.length < 4) return;

        long result;

        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i + 1; j < arr.length - 2; j++) {
                int left = j + 1;
                int right = arr.length - 1;

                while (left < right) {
                    result = arr[left] + arr[right] + arr[i] + arr[j];
                    if (result == sum) {
                        set.add(arr[i] + " " + arr[j] + " " + arr[left] + " " + arr[right]);
                    }

                    if (result >= sum) {
                        --right;
                    } else {
                        ++left;
                    }
                }
            }
        }
    }
}
