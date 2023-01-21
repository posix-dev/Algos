package sprint3.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

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

            return this.bill - o.bill > 0 ? 1 : -1; // the more bill the worse it is
        }

        return this.resolvedTasks - o.resolvedTasks > 0 ? -1 : 1;
    }
}