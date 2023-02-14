//package sprint5.sift_up;

public class Solution {

//    public static void main(String[] args) {
//        test();
//    }
    public static int siftUp(int[] heap, int idx) {
        int parentIndex = getParentIndex(idx);

        while (isValidIndex(parentIndex) && heap[parentIndex] < heap[idx]) {
            swapPositions(heap, idx, parentIndex);
            idx = parentIndex;
            parentIndex = getParentIndex(idx);
        }

        return idx;
    }

    private static boolean isValidIndex(int id) {
        return id > 0;
    }

    private static void swapPositions(int[] heap, int oldIndex, int newIndex) {
        int temp = heap[oldIndex];
        heap[oldIndex] = heap[newIndex];
        heap[newIndex] = temp;
    }

    private static int getParentIndex(int idx) {
        return idx / 2;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
        System.out.println(siftUp(sample, 5));
    }
}
