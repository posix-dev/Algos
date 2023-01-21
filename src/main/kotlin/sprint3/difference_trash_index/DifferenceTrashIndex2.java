package sprint3.difference_trash_index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DifferenceTrashIndex2 {

    // Driver function to check the above functions
    public static void main(String[] args) throws IOException {
        int n, num;
        int[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            String[] tempList = reader.readLine().split(" ");
            num = Integer.parseInt(reader.readLine());
            list = new int[n];
            int index = 0;
            for (String value : tempList) {
                list[index] = Integer.parseInt(value);
                index++;
            }
        }

        System.out.println(kthDiff(list, n, num));
    }

    // returns number of pairs with absolute
    // difference less than or equal to mid
    static int countPairs(int[] a, int n, int mid) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            // Upper bound returns pointer to position
            // of next higher number than a[i]+mid in
            // a[i..n-1]. We subtract (ub + i + 1) from
            // this position to count
            if (a[i] + mid > a[n - 1])
                res += (n - (i + 1));
            else {
                int ub = upperbound(a, n, a[i] + mid);
                res += (ub - (i + 1));
            }
        }
        return res;
    }

    // returns the upper bound
    static int upperbound(int[] a, int n, int value) {
        int low = 0;
        int high = n;
        while (low < high) {
            final int mid = (low + high) / 2;
            if (value >= a[mid])
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    // Returns k-th absolute difference
    static int kthDiff(int[] arr, int n, int k) {
        // Sort array
        Arrays.sort(arr);

        // Minimum absolute difference
        int low = arr[1] - arr[0];
        for (int i = 1; i <= n - 2; ++i)
            low = Math.min(low, arr[i + 1] - arr[i]);

        // Maximum absolute difference
        int high = arr[n - 1] - arr[0];

        // Do binary search for k-th absolute difference
        while (low < high) {
            int mid = (low + high) / 2;
            if (countPairs(arr, n, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

}
