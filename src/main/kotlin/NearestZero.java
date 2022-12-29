import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Id = 80120534
// Link: https://contest.yandex.ru/contest/22450/run-report/80120534/

public final class NearestZero {
    public static void main(@NotNull String[] args) {
        int length = 5;
        List<Integer> arr = List.of(new Integer[]{0, 1, 4, 9, 0});
        List nearestArray = nearestToZeroArray(length, arr);
        System.out.println(nearestArray);
    }

    @NotNull
    public static List<Integer> nearestToZeroArray(int length, @NotNull List<Integer> arr) {
        List<Integer> zeroArrPositions = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            if (arr.get(i) == 0) zeroArrPositions.add(i);
        }

        return findNearestPoint(arr, zeroArrPositions);
    }

    public static List<Integer> findNearestPoint(@NotNull List<Integer> arr, @NotNull List<Integer> zeroArrPositions) {
        if (zeroArrPositions.size() == 1) {
            return findDistanceFromOneEmptyHouse(arr, zeroArrPositions);
        } else {
            return findDistanceBetweenTwoEmptyHouses(arr, zeroArrPositions);
        }
    }

    private static List<Integer> findDistanceBetweenTwoEmptyHouses(List<Integer> arr, List<Integer> zeroArrPositions) {
        List<Integer> list = new ArrayList<>();
        int rightZeroPosition = 0;
        int leftIndex = Integer.MIN_VALUE;
        int rightIndex = zeroArrPositions.get(rightZeroPosition);
        int mid = (leftIndex + rightIndex) / 2;

        for (int i = 0; i < arr.size(); i++) {
            int nearestIndex;

            if (arr.get(i) != 0) {
                if (i <= mid) {
                    nearestIndex = Math.abs(i - leftIndex);
                } else {
                    nearestIndex = Math.abs(rightIndex - i);
                }
            } else {
                nearestIndex = 0;
                leftIndex = rightIndex;
                if (i != zeroArrPositions.get(zeroArrPositions.size() - 1)) {
                    rightZeroPosition += 1;
                }
                if (zeroArrPositions.size() < rightZeroPosition) {
                    rightZeroPosition = Integer.MAX_VALUE;
                }
                rightIndex = zeroArrPositions.get(rightZeroPosition);
                mid = (leftIndex + rightIndex) / 2;
            }

            list.add(i, nearestIndex);
        }

        return list;
    }

    private static List<Integer> findDistanceFromOneEmptyHouse(List<Integer> arr, List<Integer> zeroArrPositions) {
        List<Integer> list = new ArrayList<>();
        int rightZeroPosition = 0;
        int rightIndex = zeroArrPositions.get(rightZeroPosition);

        for (int i = 0; i < arr.size(); i++) {
            list.add(i, Math.abs(i - rightIndex));
        }

        return list;
    }
}

