package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Я реализовал калькулятор на обратной польской нотации(ОПН) с помощью стэка.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://steptosleep.ru/обратная-польская-нотация/
 * <p>
 * Изначально не верно понял, что нужно делать, но ссылка выше помогла.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Добавляю данные в стэк numbers(перед этим удостоверяюсь, что это число) пока не нашли операцию,
 * как только встречаю операцию, беру вместе с тем удаляя 2 элемента с вершины стека,
 * вызываю функцию calculate с данными операндами и операцией,
 * далее записываю на вершину стэка результат последней операции.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Добавление в стэк О(1), извлечение сверху так же О(1);
 * В общем случае длина операций/чисел n(входная строка) + операции над n/2 элементами
 * O(n) + O(n/2) = O(3/2n) = O(n) времени;
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Если массив содержит n элементов, то входной массив содержит n1 элементов,
 * и выходной стек должен содержать 1 элемент(конечную сумму) n1 + 1 = n;
 * <p>
 * Т.е. мой алгоритм будет потреблять O(n1) + O(1) = O(n) памяти.
 */

// Id: 80329874
// Link: https://contest.yandex.ru/contest/22781/run-report/80329874/

public class Calculator {
    public static void main(String[] args) throws IOException {
        Stack<Integer> numbers = new Stack<>();
        int operand1, operand2;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var tokenizer = new StringTokenizer(reader.readLine());
            
            while (tokenizer.hasMoreTokens()) {
                var value = tokenizer.nextToken();
                // Проблем с этим не было, просто решил сделать так =) 
                // изначально не правильно понял задачу, добавляя приоритетность
                // потом понял, как верно решать, но оставил метод, просто чтобы
                // вычислять числа и операции
                if (getPriority(value) == 0) {
                    numbers.push(Integer.valueOf(value));
                } else {
                    operand2 = numbers.pop();
                    operand1 = numbers.pop();
                    numbers.push(calculate(operand1, operand2, value));
                }
            }

        }

        System.out.println(numbers.pop());
    }

    private static int getPriority(String str) {
        char c;
        int result = 0;

        if (str.length() == 1) {
            c = str.toCharArray()[0];
        } else {
            // it means that we don't pick + - / * for sure
            c = (char) (100 + ' ');
        }

        switch (c) {
            case '+', '-' -> result = 1;
            case '*', '/' -> result = 2;
        }

        return result;
    }

    private static int calculate(double leftNumber, double rightNumber, String operation) {
        double result = switch (operation) {
            case "+" -> leftNumber + rightNumber;
            case "-" -> leftNumber - rightNumber;
            case "*" -> leftNumber * rightNumber;
            case "/" -> leftNumber / rightNumber;
            default -> 0;
        };

        // For correct result with negative number after calculating of operation
        if (result < 0) {
            result = Math.floor(result);
        }

        return (int) result;
    }

}
