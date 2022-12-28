import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


// помнить предыдущий индекс 0, если был и перезаписывать если пришел новый ноль

public final class NearestZero {
    public static final void main(@NotNull String[] args) {
        int length = 5;
        List<Integer> arr = List.of(new Integer[]{0, 1, 4, 9, 0, 5, 6, 9, 0});
        List nearestArray = nearestToZeroArray(length, arr);
        System.out.println(nearestArray);
    }

    @NotNull
    public static final List<Integer> nearestToZeroArray(int length, @NotNull List<Integer> arr) {
        List<Integer> zeroArrPositions = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            if (arr.get(i) == 0) zeroArrPositions.add(i);
        }

        return findNearestPoint(arr, zeroArrPositions);
    }

    public static final List<Integer> findNearestPoint(@NotNull List<Integer> arr, @NotNull List<Integer> zeroArrPositions) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            int nearestIndex = Integer.MAX_VALUE;

            for (int j = 0; j < zeroArrPositions.size(); j++) {
                if (i == zeroArrPositions.get(j)) {
                    nearestIndex = 0;
                    break;
                } else {
                    int absIndex = Math.abs(i - zeroArrPositions.get(j));
                    if (nearestIndex > absIndex) {
                        nearestIndex = absIndex;
                    }
                }
            }

            list.add(i, nearestIndex);
        }

        return list;
    }
}

