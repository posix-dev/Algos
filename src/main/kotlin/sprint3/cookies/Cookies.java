package sprint3.cookies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Cookies {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int children = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while(tokenizer.hasMoreTokens()) {
                var token = Integer.parseInt(tokenizer.nextToken());
                list.add(token);
            }
            int cookies = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
            while(tokenizer2.hasMoreTokens()) {
                var token = Integer.parseInt(tokenizer2.nextToken());
                list2.add(token);
            }  

            List<Integer> greedList = list.stream().sorted(Comparator.reverseOrder()).toList();
            List<Integer> cookiesSizeList = list2.stream().sorted().collect(Collectors.toList());    

            var result = 0;

            for (Integer greed : greedList) {
                if (greed <= cookiesSizeList.get(cookiesSizeList.size() - 1)) {
                    cookiesSizeList.remove(cookiesSizeList.size() - 1);
                    result++;
                    if (cookiesSizeList.isEmpty()) break;
                }
            }

             System.out.print(result);
        }
    }
}
