package sprint3.parentheses_generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParenthesesGenerator {
    
    public static void main(String[] args) throws IOException {
        int n;
        ArrayList<String> list = new ArrayList<String>();
        String str = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
        }

        getCorrectParentheses(str, 0,0, n, list);

        for(String s: list) {
            System.out.println(s);
        }
    }

    static void getCorrectParentheses(String result, int open, int close, int length, ArrayList<String> list) {
        if (open == length && close == length) {
            list.add(result.toString());
            return;
        }

        if (open < length) {
            getCorrectParentheses(result + "(", open + 1, close, length, list);
        }

        if (close < open) {
            getCorrectParentheses(result + ")", open, close + 1, length, list);
        }
    }
}
