package sprint2.limited_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MyQueueSized {

    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String PEEK = "peek";
    private static final String SIZE = "size";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandsCount = Integer.parseInt(reader.readLine().strip());
            int length = Integer.parseInt(reader.readLine().strip());
            QueueSized queueSized = new QueueSized(length);
            for (int i = 0; i < commandsCount; ++i) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                boolean isPush = tokenizer.countTokens() > 1;
                String command = tokenizer.nextToken();
                int value = 0;
                if (isPush) {
                    value = Integer.parseInt(tokenizer.nextToken());
                }

                switch (command) {
                    case PUSH:
                        queueSized.push(value);
                        break;
                    case POP:
                        queueSized.pop();
                        break;
                    case PEEK:
                        queueSized.peek();
                        break;
                    case SIZE:
                        queueSized.getSize();
                        break;
                }
            }
        }
    }
}

class QueueSized {

    private int size = 0;

    private int head = 0;
    private int tail = 0;
    private Integer[] queue;

    private QueueSized() {
    }

    public QueueSized(int length) {
        this.queue = new Integer[length];
    }

    public void push(int value) {
        if (size == queue.length) {
            System.out.println("error");
            return;
        }

        queue[tail] = value;

        if (tail == queue.length - 1) {
            tail = 0;
        } else {
            tail++;
        }

        size++;
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("None");
            return;
        }

        int value = queue[head];
        queue[head] = null;

        if (head == queue.length - 1) {
            head = 0;
        } else {
            head = head + 1;
        }

        size--;
        System.out.println(value);
    }

    public void peek() {
        if (isEmpty()) {
            System.out.println("None");
            return;
        }

        System.out.println(queue[head]);
    }

    public void getSize() {
        System.out.println(size);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

