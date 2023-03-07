package sprint7.gosha_in_restaurant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoshaInRestaurant {
    static int[] result;
    static int allCoupouns;

    public static void main(String[] args) throws IOException {
        int length;
        int[] arr;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            arr = new int[length + 1];
            result = new int[length + 1];
//            dp = new int[weight.length][profit.length];

            for (int i = 1; i <= length; i++) {
                arr[i] = Integer.parseInt(reader.readLine());
            }

            findSOme(arr, 0, 1);

            StringBuilder builder = new StringBuilder();
            int resultSum = 0;
            for (int i = 1; i < result.length; i++) {
                if (result[i] == 0) {
                    builder.append(i).append(" ");
                } else {
                    resultSum += result[i];
                }
            }

            System.out.println(resultSum + " " + allCoupouns);
            System.out.println(builder);
        }
    }

    private static void findSOme(int[] arr, int count, int index) {
        allCoupouns += count;
        while (count != 0) {
            if (index == arr.length) {
                result[index - 1] = 0;
                return;
            }
            result[index] = 0;
            index++;
            count--;
        }

        result[1] = arr[1];
        for (int i = index; i <= arr.length; i++) {
            if (arr.length - i - count == 0 || arr[i] <= 500) {
                findSOme(arr, count, i + 1);
                break;
            }
            result[i] = arr[i];
            if (arr[i] > 500) count++;
        }
    }
}
