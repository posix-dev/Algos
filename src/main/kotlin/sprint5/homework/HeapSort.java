package sprint5.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал алгоритм heapSort с priorityQueue.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98795/topics/e7dbf42a-fd5a-434b-990d-9cfe0e3a10c8/lessons/c29642e4-76ff-47df-82d2-87848ddc7f77/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * В данном алгоритме реализована priorityQueue с методами добавления с помощью метода insert(Competitor) и удалением(popMax).
 * Метод siftUp(внутри insert) перекладывает эл-т из начала на актуальную позицию, пока не будет менее приоритетен(
 * полагаясь на метод compareTo в Competitor классе), чем эл-т выше.
 * Метод siftDown(внутри метода popMax) при удалении эл-та с вершины, берёт больший по приоритету после прохода вниз по поддеревьям.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * N - кол-во эл-ов в куче.
 * Добавление в цикле всех эл-ов = O(N) * O(1)(добавление в конец) * O(logN)(просеивание вверх);
 * При удалении всё то же самое только наоборот;
 * В конечном счете временная сложность = O(N*logN)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Данный алгоритм будет занимать O(N) памяти для Node-ов;
 */

// Id: 82131192
// Link: https://contest.yandex.ru/contest/24810/run-report/82131192/

public class HeapSort {
    public static void main(String[] args) throws IOException {
        int length;
        MyPriorityQueue heap;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            heap = new MyPriorityQueue(length + 1);
            String name;
            int bill, resolvedTasks;
            for (int i = 0; i < length; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                name = tokenizer.nextToken();
                resolvedTasks = Integer.parseInt(tokenizer.nextToken());
                bill = Integer.parseInt(tokenizer.nextToken());

                heap.insert(new Competitor(name, resolvedTasks, bill));
            }

            // индексация идёт с 1 номера, а он фейковый и поэтому последний эл-т игнорим
            for (int i = 0; i < heap.getArr().length - 1; i++) {
                System.out.println(heap.popMax().getName());
            }
        }
    }

}

class MyPriorityQueue {
    private final Competitor[] arr;

    private int size = 0;

    public MyPriorityQueue(int length) {
        this.arr = new Competitor[length];
    }

    public void insert(Competitor competitor) {
        size += 1;
        arr[size] = competitor;
        siftUp(arr, size);
    }

    public void siftDown(Competitor[] heap, int idx) {
        int leftChildIndex = getChildIndex(idx);
        int rightChildIndex = leftChildIndex + 1;
        int childIndex;
        if (size >= rightChildIndex && heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0) {
            childIndex = rightChildIndex;
        } else {
            childIndex = leftChildIndex;
        }

        while (size >= childIndex && heap[idx].compareTo(heap[childIndex]) > 0) {
            swapPositions(heap, idx, childIndex);
            idx = childIndex;
            leftChildIndex = getChildIndex(idx);
            rightChildIndex = leftChildIndex + 1;

            if (size >= rightChildIndex && heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0) {
                childIndex = rightChildIndex;
            } else {
                childIndex = leftChildIndex;
            }
        }

    }

    public Competitor popMax() {
        // numeration in heap starts from 1
        Competitor c = arr[1];
        arr[1] = arr[size];
        arr[size] = null;
        size -= 1;
        siftDown(arr, 1);
        return c;
    }


    private void swapPositions(Competitor[] heap, int oldIndex, int newIndex) {
        Competitor temp = heap[oldIndex];
        heap[oldIndex] = heap[newIndex];
        heap[newIndex] = temp;
    }

    private int getChildIndex(int idx) {
        return idx * 2;
    }

    public void siftUp(Competitor[] heap, int idx) {
        int parentIndex = getParentIndex(idx);

        while (isValidIndex(parentIndex) && heap[parentIndex].compareTo(heap[idx]) > 0) {
            swapPositions(heap, idx, parentIndex);
            idx = parentIndex;
            parentIndex = getParentIndex(idx);
        }
    }

    private int getParentIndex(int idx) {
        return idx / 2;
    }

    private boolean isValidIndex(int id) {
        return id > 0;
    }

    public Competitor[] getArr() {
        return arr;
    }
}

class Competitor implements Comparable<Competitor> {
    private final String name;
    private final int resolvedTasks;
    private final int bill;

    Competitor(String name, int tasks, int bill) {
        this.name = name;
        this.resolvedTasks = tasks;
        this.bill = bill;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Competitor o) {
        if (this.resolvedTasks == o.resolvedTasks) {
            if (this.bill == o.bill) {
                return this.name.compareTo(o.name);
            }

            return -Integer.compare(o.bill, this.bill); // the more bill the worse it is
        }

        return Integer.compare(o.resolvedTasks, this.resolvedTasks);
    }
}
