package sprint3.homework.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MySolution {
    public static void main(String[] args) throws IOException {
        int length, k;
        int[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            k = Integer.parseInt(reader.readLine());
            list = new int[length];
            String firstLine = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(firstLine);
            int index = 0;
            while (tokenizer.hasMoreElements()) {
                list[index] = Integer.parseInt(tokenizer.nextToken());
                index++;
            }

            System.out.print(brokenSearch(list, k));
        }
    }

    public static int brokenSearch(int[] arr, int k) {
        if (arr[0] < arr[arr.length - 1]) {
            return brokenSearch(arr, k, Integer.MAX_VALUE);
        } else {
            int gapIndex = getGapIndex(arr);
            return brokenSearch(arr, k, gapIndex);
        }
    }

    private static int getGapIndex(int[] arr) {
        int mid;
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;

            if (mid == 0) break;

            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            } else if (arr[mid] < arr[start]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static int brokenSearch(int[] arr, int k, int gapIndex) {
        int low = 0;
        int high = arr.length;
        int mid;
        int current;
        int graphModule;
        while (low <= high) {
            mid = (low + high) / 2;
            if (mid - gapIndex == 0) graphModule = 1;
            else graphModule = 0;
            mid = mid + graphModule;
            current = arr[mid];

            if (current == k) return mid;
            if (mid == arr.length - 1) return -1;

            if (k < current) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

}
