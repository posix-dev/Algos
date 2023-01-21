package sprint3.merge_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    private static final String SORT = "sort";
    private static final String MERGE = "merge";

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int leftArraySize = mid - left;
        int rightArraySize = right - mid;

        int[] l = new int[leftArraySize];
        int[] r = new int[rightArraySize];

        System.arraycopy(arr, left, l, 0, leftArraySize);
        System.arraycopy(arr, mid, r, 0, rightArraySize);

        int leftIndex = 0, rightIndex = 0;
        int commonIndex = left;

        while (leftIndex < leftArraySize && rightIndex < rightArraySize) {
            if (l[leftIndex] <= r[rightIndex]) {
                arr[commonIndex] = l[leftIndex];
                leftIndex++;
            } else {
                arr[commonIndex] = r[rightIndex];
                rightIndex++;
            }
            commonIndex++;
        }

        while (leftIndex < leftArraySize) {
            arr[commonIndex] = l[leftIndex];
            leftIndex++;
            commonIndex++;
        }

        while (rightIndex < rightArraySize) {
            arr[commonIndex] = r[rightIndex];
            rightIndex++;
            commonIndex++;
        }

        return arr;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left < 2) return;

        int mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid, right);

        merge(arr, left, mid, right);
    }

//    public static void main(String[] args) {
//        int[] a = {1, 4, 9, 2, -10, 11};
//        int[] b = merge(a, 0, 3, 6);
//        int[] expected = {1, 2, 4, 9, 10, 11};
//        assert Arrays.equals(b, expected);
//        int[] c = {1, 4, 2, 10, 1, 2};
//        merge_sort(c, 0, 6);
//        int[] expected2 = {1, 1, 2, 2, 4, 10};
//        assert Arrays.equals(c, expected2);
//    }

    public static void main(String[] args) throws IOException {
        String command;
        int count;
        String[] list;
        int[] listInt;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            command = reader.readLine();
            count = Integer.parseInt(reader.readLine());
            listInt = new int[count];
            list = reader.readLine().split(" ");

            for (int i = 0; i < list.length; i++) {
                listInt[i] = Integer.parseInt(list[i]);
            }

            switch (command) {
                case SORT:
                    merge_sort(listInt, 0, listInt.length);
                    break;
                case MERGE:
                    merge_sort(listInt, 0, /*listInt.length / 2,*/ listInt.length);
                    break;
            }

            for (int i: listInt) {
                System.out.print(i + " ");
            }
        }

    }
}