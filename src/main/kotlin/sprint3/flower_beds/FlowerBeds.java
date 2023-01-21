package sprint3.flower_beds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FlowerBeds {
    public static void main(String[] args) throws IOException {
        int n;
        HashMap<Integer, Integer> map;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                String[] line = reader.readLine().split(" ");
                if(map.get(Integer.parseInt(line[0])) != null) {
                    int oldValue = map.get(Integer.parseInt(line[0]));
                    map.remove(Integer.parseInt(line[0]));
                    map.put(Integer.parseInt(line[1]), oldValue);
                } else {
                    if(map.get(Integer.parseInt(line[1])) == null) {
                        map.put(Integer.parseInt(line[1]), Integer.parseInt(line[0]));
                    }
                }
            }
        }

        var firstSequence = Collections.min(map.entrySet(), Map.Entry.comparingByValue());
        var secondSequence = Collections.max(map.entrySet(), Map.Entry.comparingByKey());

        if (firstSequence.getKey() > secondSequence.getValue()) {
            System.out.println(firstSequence.getValue() + " " + secondSequence.getKey());
            return;
        } else {
            System.out.println(firstSequence.getValue() + " " + firstSequence.getKey());
        }

        if(secondSequence != firstSequence) {
            System.out.println(secondSequence.getValue() + " " + secondSequence.getKey());
        }
    }
}
