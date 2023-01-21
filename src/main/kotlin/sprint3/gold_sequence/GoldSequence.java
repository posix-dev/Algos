package sprint3.gold_sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GoldSequence {
    public static void main(String[] args) throws IOException {
        int n, m;
        int[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());
            list = new int[n + m];
            String firstLine = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(firstLine);
            int indexN = 0;
            int indexM = 0;
            while (tokenizer.hasMoreTokens()) {
                list[indexN] = Integer.parseInt(tokenizer.nextToken());
                indexN++;
            }
            String secondLine = reader.readLine();
            StringTokenizer tokenizer2 = new StringTokenizer(secondLine);
            while (tokenizer2.hasMoreTokens()) {
                list[indexN + indexM] = Integer.parseInt(tokenizer2.nextToken());
                indexM++;
            }

            Arrays.sort(list);
        }

        if (list.length % 2 == 0) {
            double median = (list[list.length/2 - 1] + list[list.length/2]) / 2.0;
            if(median % 1 == 0) System.out.println((int) median); else System.out.println(median);
        } else {
            int median = list.length / 2;
            System.out.println(list[median]);
        }
    }
}
