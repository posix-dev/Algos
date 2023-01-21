package sprint3.bubble_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BubbleSort {
    public static void main(String[] args) throws IOException {
        int count;
        int[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            list = new int[count];
            int i = 0;
            while (tokenizer.hasMoreTokens()) {
                list[i] = Integer.parseInt(tokenizer.nextToken());
                i++;
            }

            bubbleSort2(list);
        }
    }

    private static void bubbleSort(int[] list) {
        boolean isShow;
        StringBuilder builder = new StringBuilder();
        boolean wasShown = false;
        for (int i = 0; i < list.length - 1; i++) {
            isShow = false;
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    isShow = true;
                    wasShown = true;
                }
            }

            if (!isShow && wasShown) break;

            for (int rew = 0; rew < list.length; rew++) {
                if (rew == list.length - 1) {
                    System.out.println(list[rew]);
                } else {
                    System.out.print(list[rew] + " ");
                }
            }
        }
    }

    public static void bubbleSort2(int[] list) {
        boolean wasShown = false;
        int lastSwap = list.length - 1;
        for (int i = 1; i < list.length; i++) {
            boolean is_sorted = true;
            int currentSwap = -1;
            for (int j = 0; j < lastSwap; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    is_sorted = false;
                    wasShown = true;
                    currentSwap = j;
                }
            }
            if (is_sorted && wasShown) break;
            lastSwap = currentSwap;

            for (int rew = 0; rew < list.length; rew++) {
                if (rew == list.length - 1) {
                    System.out.println(list[rew]);
                } else {
                    System.out.print(list[rew] + " ");
                }
            }
        }
    }
}
