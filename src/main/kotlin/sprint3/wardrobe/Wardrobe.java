package sprint3.wardrobe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Wardrobe {
    public static void main(String[] args) throws IOException {
        int count;
        int[] list;
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            list = new int[count];
            for (int i = 0; i < list.length; i++) {
                int num = Integer.parseInt(tokenizer.nextToken());
                switch (num) {
                    case 0:
                        builder1.append("0").append(" ");
                        break;
                    case 1:
                        builder2.append("1").append(" ");
                        break;
                    case 2:
                        builder3.append("2").append(" ");
                        break;
                }
            }
        }

        System.out.print(builder1.toString() + builder2 + builder3);
    }
}
