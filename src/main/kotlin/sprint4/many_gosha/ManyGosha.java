package sprint4.many_gosha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ManyGosha {
    public static void main(String[] args) throws IOException {
        int length = 0, count = 0;
        Integer[] list;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            while (tokenizer.hasMoreTokens()) {
                length = Integer.parseInt(tokenizer.nextToken());
                count = Integer.parseInt(tokenizer.nextToken());
            }

            String str = reader.readLine();
            String key;

            for (int i = 0; i < str.length() - length; i++) {
                key = str.substring(i, i + length);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            list = new Integer[map.size()];

            Iterator<Integer> iterator = Arrays.stream(map.values().toArray(list)).iterator();
            int index = 0;
            while (iterator.hasNext()) {
                if (iterator.next() >= count) System.out.print(index + " ");
                ++index;
            }
        }
    }
}
