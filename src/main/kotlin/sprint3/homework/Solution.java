package sprint3.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал поиск в сломанном массиве с помощью бинарного поиска.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://devmark.ru/article/binary-search-sorted-array
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * По факту наш массив можно сказать отсортирован, просто нужно узнать с какого момента.
 * Метод brokenSearch работает по принципу:
 * Если наш массив был склеен верно(т.е. массив отсортирован по возрастанию без неверного склеивания)
 * то произвожу обычный бинарный поиск(O(logN).
 * Иначе далее ищу gapIndex для осознания того, где происходит не верное сшитие массива.
 * Далее уже ищу в определенном промежутке наш эл-т(строки 68 и 70 соответственно).
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Полный проход по нашему алгоритму займет(без добавления в массив, иначе добавится n):
 * Если массив отсортирован уже верно, O(logN);
 * Иначе ищем gapIndex через тот же бинарный поиск и это O(logN) + последующий поиск нашего эл-та тоже O(logN).
 * В общем случае это будет просто O(logN);
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Если массив содержит n элементов,
 * то алгоритм будет потреблять O(n) памяти(если учитываем наш массив),
 * иначе O(1).
 */

// Id: 81127110
// Link: https://contest.yandex.ru/contest/23815/run-report/81127110/

public class Solution {

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
        int gapIndex = binSearchGapIndex(arr, 0, arr.length);

        if (arr[gapIndex] <= k && k <= arr[arr.length - 1]) {
            return binSearch(arr, k, gapIndex, arr.length);
        }

        return binSearch(arr, k, 0, gapIndex);
    }

    private static int binSearchGapIndex(int[] arr, int start, int end) {
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;

            if (mid + 1 < arr.length && mid - 1 >= 0) {
                if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                    return mid + 1;
                } else if (arr[mid] > arr[arr.length - 1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            } else {
                if (mid + 1 != arr.length) return mid + 1;

                return mid;
            }
        }

        return -1;
    }

    private static int binSearch(int[] arr, int k, int start, int end) {
        int mid;
        int left = start;
        int right = end;

        while (left < right) {
            mid = (left + right) / 2;

            if (arr[mid] == k) return mid;

            if (k < arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
