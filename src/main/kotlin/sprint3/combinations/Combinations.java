package sprint3.combinations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Combinations {
    private static final String TWO = "abc";
    private static final String THREE = "def";
    private static final String FOUR = "ghi";
    private static final String FIVE = "jkl";
    private static final String SIX = "mno";
    private static final String SEVEN = "pqrs";
    private static final String EIGHT = "tuv";
    private static final String NINE = "wxyz";

    static String[] table = { "0", "1", TWO, THREE, FOUR,FIVE, SIX, SEVEN, EIGHT, NINE };

    public static void main(String[] args) throws IOException {
       List<Integer> list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());            
            list = Arrays.stream(tokenizer.nextToken()
                .split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        }

        System.out.println(getCombinations(list, list.size(), table));
    }

    private static String getCombinations(List<Integer> list, int length, String[] table) {
        Queue<String> q = new LinkedList<>();
        StringBuilder str = new StringBuilder();
        q.add("");

        while(!q.isEmpty()) {
            String letter = q.remove();

            if (letter.length() == length) {
                str.append(letter).append(" ");
            } else {
                String value = table[list.get(letter.length())];
                for (int i = 0; i < value.length(); i++) {
                    q.add(letter + value.charAt(i));
                }
            }
        }

        return str.toString();
    }

}
