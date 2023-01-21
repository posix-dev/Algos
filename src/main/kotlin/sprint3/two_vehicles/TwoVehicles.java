package sprint3.two_vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoVehicles {
    public static void main(String[] args) throws IOException {
        int count, vehiclePrice;
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
            vehiclePrice = Integer.parseInt(reader.readLine());
        }

        int first = binarySearch(list, vehiclePrice, 0, list.length - 1);
        int second = binarySearch(list, 2 * vehiclePrice, 0, list.length - 1);

        System.out.println(first + " " + second);
    }

    private static int binarySearch(int[] list, int vehiclePrice, int left, int right) {
        if (right <= left && left != 0) return -1;

        int mid = (left + right) / 2;

        if (list[mid] >= vehiclePrice && list[mid - 1] < vehiclePrice) {
            return mid + 1;
        } else if (vehiclePrice <= list[mid]) {
            return binarySearch(list, vehiclePrice, left, mid);
        } else {
            return binarySearch(list, vehiclePrice, mid + 1, right);
        }
    }

}
