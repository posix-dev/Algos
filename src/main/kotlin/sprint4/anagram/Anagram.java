package sprint4.anagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Anagram {
    public static void main(String[] args) throws IOException {
        String[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int length = Integer.parseInt(reader.readLine());
            arr = reader.readLine().split(" ");
            boolean[] positions = new boolean[length];
            StringBuilder builder = new StringBuilder();

            startAnagram(arr, positions, builder, 0);
        }
    }

    private static void startAnagram(String[] arr, boolean[] positions, StringBuilder builder, int index) {
        if(index == arr.length) return;
        if (arr.length == 1) {
            for (int i = 0; i < positions.length; i++) {
                if (!positions[i]) {
                    System.out.println(i);
                    return;
                }
            }
            return;
        }
        positions[index] = true;
        int newLength = arr.length;
        char[] a = arr[index].toCharArray();
        Arrays.sort(a);

        for (int j = index + 1; j < arr.length; j++) {
            if (arr[j] == null) continue;
            char[] sortedCharArr = arr[j].toCharArray();
            Arrays.sort(sortedCharArr);
            if (Arrays.toString(a).equals(Arrays.toString(sortedCharArr))) {
                builder.append(j).append(" ");
                arr[j] = null;
                positions[j] = true;
                newLength -= 1;
            }
        }

        builder.insert(0, index + " ");
        System.out.println(builder);
        builder.setLength(0);

        String[] result = new String[newLength];/*Arrays.copyOf(arr, newLength - 1)*/
        int j = 0;
        for(int i =0; i < newLength; i++) {
            while(arr[j] == null) j++;
            result[i] = arr[j];
            j++;
        }

        startAnagram(result, positions, builder, ++index);
    }
}
