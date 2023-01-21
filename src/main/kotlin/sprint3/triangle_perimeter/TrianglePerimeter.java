package sprint3.triangle_perimeter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrianglePerimeter {
    public static void main(String[] args) throws IOException {
        int count;
        int[] list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int i = 0;
            list = new int[count];
            while (tokenizer.hasMoreTokens()) {
                list[i] = Integer.parseInt(tokenizer.nextToken());
                i++;
            }
            Arrays.sort(list);
        }

        for (int i = list.length - 1; i > 1; i--) {
            int biggestNumber = list[i];
            if (biggestNumber < list[i - 1] + list[i - 2]) {
                System.out.println(biggestNumber + list[i - 1] + list[i - 2]);
                return;
            }
        }
    }
}
