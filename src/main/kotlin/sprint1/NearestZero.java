package sprint1;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Id = 80182539
// Link: https://contest.yandex.ru/contest/22450/run-report/80182539/

public final class NearestZero {
    public static void main(@NotNull String[] args) throws IOException {
        List<Integer> arr;
        List<Integer> leftToRight;
        List<Integer> finalList;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int length = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            leftToRight = getFromLeftToRightList(arr);
            finalList = getFinalList(leftToRight, arr);
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = finalList.size() - 1; i >= 0; i--) {
            writer.write(String.valueOf(finalList.get(i)));
            writer.write(" ");
        }

        writer.flush();
    }

    private static List<Integer> getFinalList(List<Integer> leftToRight, List<Integer> arr) {
        List<Integer> finalList = new ArrayList<>();
        int counter = arr.size();
        for (int j = arr.size() - 1; j >= 0; j--) {
            if (arr.get(j) == 0) {
                counter = 0;
                finalList.add(counter);
            } else {
                counter++;
                if (leftToRight.get(j) <= counter) {
                    finalList.add(leftToRight.get(j));
                } else {
                    finalList.add(counter);
                }
            }
        }

        return finalList;
    }

    private static List<Integer> getFromLeftToRightList(List<Integer> arr) {
        int counter = arr.size();
        List<Integer> leftToRight = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == 0) {
                counter = 0;
            } else {
                counter++;
            }
            leftToRight.add(counter);
        }

        return leftToRight;
    }
}

