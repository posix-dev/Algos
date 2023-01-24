package sprint3.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал эффективную quick sort без доп выделения на промежуточную память.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://javarush.com/groups/posts/1939-comparator-v-java
 * https://stackabuse.com/quicksort-in-java/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Реализация данного алгоритма базируется на quick sort сортировке.
 * Для сравнения между эл-ми воспользовался известным интерфейсом Comparable c методом compareTo,
 * где описал наши правила сортировки.
 * В начале метода quickSort выбираю рандомно pivot(опорный эл-т) и по разные границы от него смотрю
 * если эл-т с левой границы меньше опорного, то перемещаю границу правее у левой границы(left) пока не дойду
 * до эл-та, который больше pivot или есть сам pivot.
 * С правой границы смотрю, что если наш эл-т больше pivot(опорного) то сдвигаю правуб границу(right) левее, пока
 * не дойду до pivot или эл-та, который больше right.
 * В конце итерации меняю местами наши элементы на позициях left & right местами и сдвигаю left правее и right левее.
 * Эти итерации продолжаются пока левая граница не дошла до правой или пересекла её.
 * Метод quickSort будет вызываться рекурсивно пока startIndex не больше endIndex для двух частей массива.
 * От start до right и left до end;
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Скорость сортировки, к сожалению, зависит от верно подобранного pivot(опорный эл-т);
 * В среднем данная сортировка будет работать за O(N * logN) времени;
 * В худшем случае сортировка будет работать за O(N^2) времени, если pivot будет указывать на последний или
 * первые эл-т отсортированного массива(к примеру всегда pivot всегда < current эл-та) и мы пройдем весь массив;
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Если массив содержит n элементов, то алгоритм будет потреблять O(N) памяти.
 * Если рассмотреть того, что есть стек вызовов в рекурсии, то в среднем получится
 * O(N + logN) = O(N) памяти или в худшем O(2N) = O(N) памяти;
 */

// В пространственной сложности не совсем понял претензию, тк вроде всё описал.
// Id: 81124631
// Link: https://contest.yandex.ru/contest/23815/run-report/81124631/

public class EffectiveQuickSort {
    public static void main(String[] args) throws IOException {
        int length;
        Intern[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            list = new Intern[length];
            String name;
            int bill, resolvedTasks;
            for (int i = 0; i < length; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                name = tokenizer.nextToken();
                resolvedTasks = Integer.parseInt(tokenizer.nextToken());
                bill = Integer.parseInt(tokenizer.nextToken());

                list[i] = new Intern(name, resolvedTasks, bill);
            }

            quickSort(list, 0, length - 1);

            for (Intern intern : list) {
                System.out.println(intern.getName());
            }
        }
    }

    private static void quickSort(Intern[] list, int start, int end) {
        if (start > end) return;

        int pivotIndex = ThreadLocalRandom.current().nextInt(start, end + 1);
        Intern pivot = list[pivotIndex];

        int left = start;
        int right = end;

        while (left <= right) {
            while (pivot.compareTo(list[left]) > 0) { // pivot more than list.get(start)
                left += 1;
            }
            while (pivot.compareTo(list[right]) < 0) { //pivot less than list.get(end)
                right -= 1;
            }
            if (left <= right) {
                swap(list, left, right);
                left += 1;
                right -= 1;
            }
        }

        quickSort(list, start, right);
        quickSort(list, left, end);
    }

    private static void swap(Intern[] list, int start, int end) {
        Intern temp = list[start];
        list[start] = list[end];
        list[end] = temp;
    }
}

class Intern implements Comparable<Intern> {
    private String name;
    private int resolvedTasks;
    private int bill;

    Intern(String name, int tasks, int bill) {
        this.name = name;
        this.resolvedTasks = tasks;
        this.bill = bill;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Intern o) {
        if (this.resolvedTasks == o.resolvedTasks) {
            if (this.bill == o.bill) {
                return this.name.compareTo(o.name);
            }

            return -Integer.compare(o.bill, this.bill); // the more bill the worse it is
        }

        return Integer.compare(o.resolvedTasks, this.resolvedTasks);
    }
}