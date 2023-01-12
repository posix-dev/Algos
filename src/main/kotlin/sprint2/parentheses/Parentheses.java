package sprint2.parentheses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class Parentheses {

    public static void main(String[] args) throws IOException {
        String[] arr;
        Stack<String> stack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            arr = reader.readLine().split("");
        }

        if (arr.length == 1) {
            System.out.println("True");
            return;
        }

        if (arr.length % 2 != 0) {
            System.out.println("False");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            String cl = getCloseParentheses(arr[i]);
            if (cl == null) {
                stack.push(arr[i]);
            } else {
                if (stack.isEmpty() || !Objects.equals(stack.pop(), cl)) {
                    System.out.println("False");
                    return;
                }
            }
        }

        System.out.println("True");
    }

    static String getCloseParentheses(String openParentheses) {
        switch (openParentheses) {
            case "}":
                return "{";
            case ")":
                return "(";
            case "]":
                return "[";
            default:
                return null;
        }
    }
}
