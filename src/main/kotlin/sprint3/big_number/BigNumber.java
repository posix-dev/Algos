package sprint3.big_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BigNumber {
    public static void main(String[] args) throws IOException {
        int count;
        ArrayList<String> list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            list = new ArrayList<>(count);
            while (tokenizer.hasMoreTokens()) {
                list.add(tokenizer.nextToken());
            }

            list.sort((s1, s2) -> {
                String XY = s1 + s2;
                String YX = s2 + s1;
                if (XY.compareTo(YX) == 0) return 0;

                return XY.compareTo(YX) > 0 ? -1 : 1;
            });
        }

        for (String s : list) {
            System.out.print(s);
        }
    }
}
