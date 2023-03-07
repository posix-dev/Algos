package sprint7.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал алгоритм о рюкзаке.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98797/topics/18d9b322-aae7-4ada-bf94-157d65f03181/lessons/8068d915-bc26-4b1f-8f0a-4797f4ccb77a/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * В данном алгоритме изначально вычисляю сумму чисел и если остаток от деления не равен 0, то два массива с одинаковой суммой
 * мы не сможем найти.
 * Создаю массив dp со значением до полусуммы.
 * Далее проверка на число равное и далее больше halfSum.
 * Во втором цикле иду от значения halfSum до нынешнего значения в values(values[i-1]) и записываю в массив
 * либо старое значение, либо (j - values[i-1]). В конце мы найдем корректный ответ в последней ячейке
 * dp массива.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * N - длина массива чисел;
 * В данном алгоритме временная сложность O(N * N/half);
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Данный алгоритм будет занимать O(N) памяти чисел(values) + O(half) памяти;
 */

// Id: 83251720
// Link: https://contest.yandex.ru/contest/25597/run-report/83251720/

public class TheSameAmount {

    static int[] values;
    static boolean[] dp;

    public static void main(String[] args) throws IOException {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());

            values = new int[n];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                values[i] = Integer.parseInt(tokenizer.nextToken());
            }

            if (isTheSameAmount()) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    private static boolean isTheSameAmount() {
        int sum = Arrays.stream(values).sum();

        if (sum % 2 != 0) return false;

        int half = sum / 2;
        dp = new boolean[half + 1];
        dp[0] = true; // basic case

        for (int i = 1; i < values.length + 1; i++) {
            if (values[i - 1] == half) return true;
            if (values[i - 1] > half) return false;

            for (int j = half; j > values[i - 1] - 1; j--)
                dp[j] = dp[j - values[i - 1]] || dp[j];
        }

        return dp[dp.length - 1];
    }
}
