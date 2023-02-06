package sprint4.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Competition {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int length = Integer.parseInt(reader.readLine());
            list = new ArrayList<>(length);
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int element, value;
            int r = 0;

            while (tokenizer.hasMoreElements()) {
                element = Integer.parseInt(tokenizer.nextToken());
                if (element == 0) value = -1;
                else value = 1;
                list.add(value);
            }

            int here;
            for (int i = 0; i < list.size(); i++) {
                int result = 0;

                for (int j = i; j < list.size(); j++) {
                    result += list.get(j);
                    here = Math.abs(i - j);

                    if (result == 0 && here > r) {
                        r = here;
                    }
                }
            }

            if (r == 0) System.out.println(0);
            else System.out.println(r + 1);
        }
    }
}