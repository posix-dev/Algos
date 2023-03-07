package sprint7.gold_fever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class GoldFever {
    public static void main(String[] main) throws IOException {
        int length, loadCapacity;
        ArrayList<GoldSection> goldContainer;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            loadCapacity = Integer.parseInt(reader.readLine());
            length = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            goldContainer = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int cost = Integer.parseInt(tokenizer.nextToken());
                int kilos = Integer.parseInt(tokenizer.nextToken());

                goldContainer.add(new GoldSection(cost, kilos));
            }
            Collections.sort(goldContainer);

            long result = 0;
            int i = 0;
            GoldSection item;
            while (loadCapacity > 0 && i < goldContainer.size()) {
                item = goldContainer.get(i);
                if (item.kilos <= loadCapacity) {
                    result += (item.cost * item.kilos);
                    loadCapacity -= item.kilos;
                    item.kilos = 0;
                } else {
                    while (item.kilos > 0 && loadCapacity > 0) {
                        result += item.cost;
                        item.kilos -= 1;
                        loadCapacity -= 1;
                    }
                }
                ++i;
            }

            System.out.println(result);
        }
    }
}

class GoldSection implements Comparable<GoldSection> {
    long kilos;

    long cost;

    GoldSection(int cost, int kilos) {
        this.cost = cost;
        this.kilos = kilos;
    }

    public long getKilos() {
        return kilos;
    }

    public void setKilos(int kilos) {
        this.kilos = kilos;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(GoldSection o) {
        return Long.compare(o.cost, this.cost);
    }
}
