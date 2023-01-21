//package sprint3.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int brokenSearch(int[] arr, int k) {
        int gapIndex;

        if (arr[0] < arr[arr.length - 1])
            return binSearch(arr, k, 0, arr.length);

        gapIndex = binSearchGapIndex(arr, 0, arr.length);

        if (arr[gapIndex] <= k && k <= arr[arr.length - 1])
            return binSearch(arr, k, gapIndex, arr.length);
        return binSearch(arr, k, 0, gapIndex);
    }

    private static int binSearchGapIndex(int[] arr, int min, int max) {
        int mid;

        while (min <= max) {
            mid = (min + max) / 2;

            if (mid + 1 < arr.length && mid - 1 >= 0) {
                if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1])
                    return mid + 1;
                else if (arr[mid] > arr[arr.length - 1])
                    min = mid + 1;
                else
                    max = mid;
            } else {
                if (mid + 1 != arr.length)
                    return mid + 1;
                return mid;
            }
        }
        return -1;
    }

    private static int binSearch(int[] arr, int k, int indexStart, int indexEnd) {
        int mid;
        int min = indexStart;
        int max = indexEnd;

        while (min < max) {
            mid = (min + max) / 2;

            if (arr[mid] == k)
                return mid;
            else if (k < arr[mid])
                max = mid;
            else
                min = mid + 1;
        }
        return -1;
    }
}
