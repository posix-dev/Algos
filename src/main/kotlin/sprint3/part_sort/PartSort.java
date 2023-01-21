package sprint3.part_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PartSort {
    public static void main(String[] args) throws IOException {
        int n;
        ArrayList<Integer> list;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            list = new ArrayList<Integer>(n);
            while(tokenizer.hasMoreTokens()) {
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }

            
            int minIndex = list.indexOf(Collections.min(list));
            int max = Collections.max(list);
            System.out.println(findBlocks(list, minIndex + 1, 1, max, Integer.MIN_VALUE));
        }

    }

    private static int findBlocks(
        List<Integer> list, int minIndex, int result, int maxInList,
        int leftPreviousMax
        ) {
        if (list.size() == 1) return result;

        List<Integer> left = list.subList(0, minIndex);
        List<Integer> right = list.subList(minIndex, list.size());

        if(right.isEmpty()) return result;
        
        int maxLeft = Collections.max(left);
        int minRight = Collections.min(right);

        if (maxLeft < minRight && leftPreviousMax < minRight) {
            int minInd = right.indexOf(Collections.min(right));
            int startPosition = minInd + 1;    
            result = findBlocks(right, startPosition, ++result, maxInList, Integer.MIN_VALUE);
        } else {
            if(maxInList == maxLeft) return result;
            int minInd = right.indexOf(Collections.min(right));
            int startPosition = minInd + 1;    
            result = findBlocks(right, startPosition, result, maxInList, maxLeft);
        }

        return result;
    }

}
