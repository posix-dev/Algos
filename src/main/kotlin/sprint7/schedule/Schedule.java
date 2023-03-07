package sprint7.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Schedule {

    public static void main(String[] args) throws IOException {
        final int length;
        ArrayList<ScheduleItem> schedule;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            length = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer;

            schedule = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                double gapStart = Double.parseDouble(tokenizer.nextToken());
                double gapEnd = Double.parseDouble(tokenizer.nextToken());

                schedule.add(new ScheduleItem(gapStart, gapEnd));
            }
            Collections.sort(schedule);

            ArrayList<ScheduleItem> finalList = new ArrayList<>();
            ScheduleItem current;
            ScheduleItem prev = schedule.get(0);
            finalList.add(prev);
            for (int i = 1; i < schedule.size(); i++) {
                current = schedule.get(i);
                if (prev.end <= current.start) {
                    prev = current;
                    finalList.add(current);
                }
            }

            System.out.println(finalList.size());

            for (ScheduleItem item : finalList) System.out.println(item);
        }
    }
}

class ScheduleItem implements Comparable<ScheduleItem> {
    double start;
    double end;

    ScheduleItem(double start, double end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(ScheduleItem o) {
        if (this.end == o.end) return Double.compare(this.start, o.start);
        else return Double.compare(this.end, o.end);
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        if (this.start % 1 == 0) {
            resultStr.append((int) this.start);
        } else {
            resultStr.append(this.start);
        }

        resultStr.append(" ");

        if (this.end % 1 == 0) {
            resultStr.append((int) this.end);
        } else {
            resultStr.append(this.end);
        }

        return resultStr.toString();
    }
}
