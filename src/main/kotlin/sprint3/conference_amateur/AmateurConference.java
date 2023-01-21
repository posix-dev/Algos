package sprint3.conference_amateur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AmateurConference {
    public static void main(String[] args) throws IOException {
        int ids, key;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        List<Map.Entry<Integer, Integer> > list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                key = Integer.parseInt(tokenizer.nextToken());
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            ids = Integer.parseInt(reader.readLine());
            list = new ArrayList<>(map.entrySet());
            list.sort((o1, o2) -> {
                if (Objects.equals(o1.getValue(), o2.getValue())) return o1.getKey() - o2.getKey();
                else return o2.getValue() - o1.getValue();
            });
        }

        for (int i = 0; i < ids; i++) {
            System.out.print(list.get(i).getKey() + " ");
        }

    }
}
