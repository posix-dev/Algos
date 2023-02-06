package sprint4.circles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class Circles {
    public static void main(String[] args) throws IOException {
        int length;
        LinkedHashSet<String> set = new LinkedHashSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            for (int i = 0; i < length; i++) {
                String str = reader.readLine();
                set.add(str);
            }

            for (String s : set) {
                System.out.println(s);
            }
        }
    }
}
