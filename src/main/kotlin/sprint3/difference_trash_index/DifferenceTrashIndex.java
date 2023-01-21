package sprint3.difference_trash_index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DifferenceTrashIndex {
    public static void main(String[] args) throws IOException {
        int n, num;
        int[] list;
        ArrayList<Integer> listDifs;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            String[] tempList = reader.readLine().split(" ");
            num = Integer.parseInt(reader.readLine());
            list = new int[n];
            listDifs = new ArrayList<>(n * (n - 1) / 2);
            int index = 0;
            for (String value : tempList) {
                list[index] = Integer.parseInt(value);
                index++;
            }

            for (int i = 0; i < list.length; i++) {
                for (int j = i + 1; j < list.length; j++) {
                    listDifs.add(Math.abs(list[i] - list[j]));
                }
            }

            Collections.sort(listDifs);

            System.out.println(listDifs.get(num - 1));
        }

    }
}
