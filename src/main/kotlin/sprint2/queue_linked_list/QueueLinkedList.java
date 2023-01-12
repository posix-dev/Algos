package sprint2.queue_linked_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class QueueLinkedList {

    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String SIZE = "size";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandsCount = Integer.parseInt(reader.readLine().strip());
            QueueViaLinkedList q = new QueueViaLinkedList();
            for (int i = 0; i < commandsCount; ++i) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                boolean isPush = tokenizer.countTokens() > 1;
                String command = tokenizer.nextToken();
                int value = 0;
                if (isPush) {
                    value = Integer.parseInt(tokenizer.nextToken());
                }

                switch (command) {
                    case GET:
                        q.get();
                        break;
                    case PUT:
                        q.put(value);
                        break;
                    case SIZE:
                        q.getSize();
                        break;
                }
            }
        }
    }
}

class QueueViaLinkedList {
    private int head = 0;
    private final List<Integer> list;

    public QueueViaLinkedList() {
        this.list = new LinkedList<>();
    }

    public void put(int value) {
        list.add(value);
    }

    public void get() {
        if (isEmpty()) {
            System.out.println("error");
            return;
        }

        System.out.println(list.get(head));
        list.remove(head);
    }

    public void getSize() {
        System.out.println(list.size());
    }

    boolean isEmpty() {
        return list.isEmpty();
    }
}
