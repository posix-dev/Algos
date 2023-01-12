package sprint2.stack_max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StackMax {

    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String GET_MAX = "get_max";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandsCount = Integer.parseInt(reader.readLine().strip());
            MyStack stack = new MyStack();
            for (int i = 0; i < commandsCount; ++i) {
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line);
                boolean isPush = tokenizer.countTokens() > 1;
                String command = tokenizer.nextToken();
                int value = 0;
                if (isPush) {
                    value = Integer.parseInt(tokenizer.nextToken());
                }

                switch (command) {
                    case PUSH:
                        stack.push(value);
                        break;
                    case POP:
                        stack.pop();
                        break;
                    case GET_MAX:
                        stack.getMax();
                        break;
                }
            }
        }
    }

}

class MyStack {

    private final Stack<Integer> maxStack;
    private final Stack<Integer> stack;

    public MyStack() {
        this.stack = new Stack<>();
        this.maxStack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);

        if (maxStack.isEmpty()) {
            maxStack.push(value);
            return;
        }

        int maxElement;
        if (value > maxStack.peek()) {
            maxElement = value;
        } else {
            maxElement = maxStack.peek();
        }

        maxStack.push(maxElement);
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("error");
            return;
        }

        stack.pop();
        maxStack.pop();
    }

    public int getMax() {
        if (maxStack.isEmpty()) {
            System.out.println("None");
            return -1;
        }

        System.out.println(maxStack.peek());
        return maxStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
