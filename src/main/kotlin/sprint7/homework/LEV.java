package sprint7.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал алгоритм минимального расстояния по Левенштейну.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://habr.com/ru/post/676858/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Данный алгоритм основан на алгоритме Вагнера-Фишера, где нам нужно вычислить минимум в матрице по формуле ниже
 * D(i, j) = min {
 * D(i - 1, j) + цена удаления символа S1[i],
 * D(i, j - 1) + цена вставки символа S2[j],
 * D(i - 1, j - 1) + цена замены символа S1[i] на символ S2[j]
 * }
 * Были созданы два массива для нахождения мин расстояния Левенштейна.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * M - длина символов 1-ой строки;
 * N - длина символов 2-ой строки;
 * <p>
 * В данном алгоритме временная сложность O(M * N) + так же перезапись O(N) символов = O(M * N^2);
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Данный алгоритм будет занимать O(M+N) памяти;
 */

// Id: 83245073
// Link: https://contest.yandex.ru/contest/25597/run-report/83245073/

public class LEV {

    public static void main(String[] args) throws IOException {
        char[] str1, str2;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine().toCharArray();
            str2 = reader.readLine().toCharArray();

            System.out.println(getLEVDistance(str1, str2));
        }
    }

    private static int getLEVDistance(char[] str1, char[] str2) {
        int[] dpFirst = new int[str2.length + 1];
        int[] dpSecond = new int[str2.length + 1];

        for (int i = 0; i <= str2.length; i++) dpSecond[i] = i;

        for (int i = 1; i <= str1.length; i++) {
            System.arraycopy(dpSecond, 0, dpFirst, 0, dpSecond.length);

            dpSecond[0] = i;
            for (int j = 1; j <= str2.length; j++) {
                dpSecond[j] = getMinDistance(
                        dpFirst[j - 1] + getReplacementDiff(str1[i - 1], str2[j - 1]),
                        dpSecond[j - 1] + 1,
                        dpFirst[j] + 1
                );
            }
        }

        return dpSecond[dpSecond.length - 1];
    }

    static int getReplacementDiff(char firstCh, char secondCh) {
        return firstCh == secondCh ? 0 : 1;
    }

    private static int getMinDistance(int... num) {
        return Arrays.stream(num).min().orElse(Integer.MAX_VALUE);
    }
}
