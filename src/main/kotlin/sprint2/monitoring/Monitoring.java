package sprint2.monitoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Monitoring {
    public static void main(String[] args) throws IOException {
        int m,n;
        StringBuilder transportedMatrix = new StringBuilder();
        String[][] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            m = Integer.parseInt(reader.readLine());
            n = Integer.parseInt(reader.readLine());

            arr = new String[m][n];

            int j;
            for (int i = 0; i < m; i++) {
                j = 0;
                for(String s: reader.readLine().split(" ")) {
                    arr[i][j] = s;
                    j++;
                }
            }

            for(int i = 0; i < n; i++) {
                for(int jj = 0; jj < m; jj++) {
                    transportedMatrix.append(arr[jj][i]).append(" ");
                }
                transportedMatrix.append("\n");
            }
        }

        System.out.println(transportedMatrix);
    }
}
