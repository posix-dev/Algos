package sprint4.closest_stop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClosestStop {

    private static final int EXPONENT = 2;

    public static void main(String[] args) throws IOException {
        int length1;
        int length2;
        ArrayList<XYPair> exits;
        ArrayList<XYPair> stops;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length1 = Integer.parseInt(reader.readLine());
            exits = new ArrayList<>(length1);
            for (int i = 0; i < length1; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(tokenizer.nextToken());
                int y = Integer.parseInt(tokenizer.nextToken());
                exits.add(new XYPair(x, y));
            }

            length2 = Integer.parseInt(reader.readLine());
            stops = new ArrayList<>(length1);
            for (int i = 0; i < length2; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(tokenizer.nextToken());
                int y = Integer.parseInt(tokenizer.nextToken());
                stops.add(new XYPair(x, y));
            }

            int result;
            int biggestResult = 0;
            int key = 0;

            for (int i = 0; i < length1; i++) {
                result = 0;

                for (int j = 0; j < length2; j++) {
                    if (getResult(exits.get(i), stops.get(j)) < 401) {
                        ++result;
                    }
                }

                if (biggestResult < result) {
                    key = i + 1;
                    biggestResult = result;
                }
            }

            System.out.println(key);
        }
    }

    private static double getResult(XYPair first, XYPair second) {
        double doubleX = Math.pow(first.getX() - second.getX(), EXPONENT);
        double doubleY = Math.pow(first.getY() - second.getY(), EXPONENT);
        return doubleX + doubleY;
    }

}

class XYPair {
    private final int x;
    private final int y;

    XYPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
