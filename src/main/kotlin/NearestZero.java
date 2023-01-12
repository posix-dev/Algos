import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Id = 80120534
// Link: https://contest.yandex.ru/contest/22450/run-report/80120534/

public final class NearestZero {
    public static void main(@NotNull String[] args) {
        List<Integer> arr = List.of(new Integer[]{10, 13, 31, 35, 39, 0, 0, 59, 0, 66, 68, 73, 74, 0, 0, 0, 87, 89, 96, 99});
        List<Integer> leftToRight = getFromLeftToRightList(arr);
        List<Integer> finalList = getFinalList(leftToRight, arr);

        for (int i = finalList.size() - 1; i >= 0; i--) {
            System.out.println(finalList.get(i));
        }
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

//    @NotNull
//    public static List<Integer> nearestToZeroArray(List<Integer> arr) {
//        List<Integer> zeroArrPositions = new ArrayList<>();
//
//        for (int i = 0; i < arr.size(); i++) {
//            if (arr.get(i) == 0) zeroArrPositions.add(i);
//        }
//
//        return findNearestPoint(arr, zeroArrPositions);
//    }

    public static List<Integer> findNearestPoint(List<Integer> arr, List<Integer> zeroArrPositions) {
        List<Integer> list = new ArrayList<>();
        int rightZeroPosition = 0;
        int leftIndex = Integer.MIN_VALUE;
        int rightIndex = zeroArrPositions.get(rightZeroPosition);
        int mid = (leftIndex + rightIndex) / 2;

        if (zeroArrPositions.size() == 1) {
            for (int i = 0; i < arr.size(); i++) {
                list.add(i, Math.abs(i - rightIndex));
            }
            return list;
        }

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

//    public static List<Integer> findNearestPoint(@NotNull List<Integer> arr, @NotNull List<Integer> zeroArrPositions) {
//        if (zeroArrPositions.size() == 1) {
//            return findDistanceFromOneEmptyHouse(arr, zeroArrPositions);
//        } else {
//            return findDistanceBetweenTwoEmptyHouses(arr, zeroArrPositions);
//        }
//    }
//
//    private static List<Integer> findDistanceBetweenTwoEmptyHouses(List<Integer> arr, List<Integer> zeroArrPositions) {
//        List<Integer> list = new ArrayList<>();
//        int rightZeroPosition = 0;
//        int leftIndex = Integer.MIN_VALUE;
//        int rightIndex = zeroArrPositions.get(rightZeroPosition);
//        int mid = (leftIndex + rightIndex) / 2;
//
//        for (int i = 0; i < arr.size(); i++) {
//            int nearestIndex;
//
//            if (arr.get(i) != 0) {
//                if (i <= mid) {
//                    nearestIndex = Math.abs(i - leftIndex);
//                } else {
//                    nearestIndex = Math.abs(rightIndex - i);
//                }
//            } else {
//                nearestIndex = 0;
//                leftIndex = rightIndex;
//                if (i != zeroArrPositions.get(zeroArrPositions.size() - 1)) {
//                    rightZeroPosition += 1;
//                }
//                if (zeroArrPositions.size() < rightZeroPosition) {
//                    rightZeroPosition = Integer.MAX_VALUE;
//                }
//                rightIndex = zeroArrPositions.get(rightZeroPosition);
//                mid = (leftIndex + rightIndex) / 2;
//            }
//
//            list.add(i, nearestIndex);
//        }
//
//        return list;
//    }
//
//    private static List<Integer> findDistanceFromOneEmptyHouse(List<Integer> arr, List<Integer> zeroArrPositions) {
//        List<Integer> list = new ArrayList<>();
//        int rightZeroPosition = 0;
//        int rightIndex = zeroArrPositions.get(rightZeroPosition);
//
//        for (int i = 0; i < arr.size(); i++) {
//            list.add(i, Math.abs(i - rightIndex));
//        }
//
//        return list;
//    }
}

