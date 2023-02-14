package sprint5.sift_down;

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static int siftDown(int[] heap, int idx) {
        int leftChildIndex = getChildIndex(idx);
        int rightChildIndex = leftChildIndex + 1;
        int childIndex;
        if (leftChildIndex < heap.length && rightChildIndex < heap.length && heap[leftChildIndex] < heap[rightChildIndex]) {
            childIndex = rightChildIndex;
        } else {
            childIndex = leftChildIndex;
        }

        while (childIndex < heap.length && heap[idx] < heap[childIndex]) {
            swapPositions(heap, idx, childIndex);
            idx = childIndex;
            leftChildIndex = getChildIndex(idx);
            rightChildIndex = leftChildIndex + 1;

            if (leftChildIndex < heap.length && rightChildIndex < heap.length && heap[leftChildIndex] < heap[rightChildIndex]) {
                childIndex = rightChildIndex;
            } else {
                childIndex = leftChildIndex;
            }
        }

        return idx;
    }


    private static void swapPositions(int[] heap, int oldIndex, int newIndex) {
        int temp = heap[oldIndex];
        heap[oldIndex] = heap[newIndex];
        heap[newIndex] = temp;
    }

    private static int getChildIndex(int idx) {
        return idx * 2;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
        System.out.println(siftDown(sample, 2));
    }
}
