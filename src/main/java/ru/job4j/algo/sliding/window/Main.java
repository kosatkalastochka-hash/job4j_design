package ru.job4j.algo.sliding.window;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", start, end);
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }
}

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }
        Queue<Interval> activeIntervals = new PriorityQueue<>(Comparator.comparingInt(Interval::getEnd));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        intervals.sort(Comparator.comparing(Interval::getStart));

        for (Interval interval : intervals) {
            while (!activeIntervals.isEmpty() && activeIntervals.peek().getEnd() <= interval.start) {
                activeIntervals.poll();
            }
            activeIntervals.offer(interval);

            int currentOverlap = activeIntervals.size();

            if (currentOverlap > maxOverlap) {
                maxOverlap = currentOverlap;
                maxStart = interval.getStart();
                maxEnd = activeIntervals.peek().getEnd();

            } else if (currentOverlap == maxOverlap) {

                int currentStart = interval.getStart();
                int currentEnd = activeIntervals.peek().getEnd();

                if ((currentEnd - currentStart) < (maxEnd - maxStart)) {
                    maxStart = currentStart;
                    maxEnd = currentEnd;
                }
            }

        }

        return new int[]{
                maxStart, maxEnd
        };
    }

    public static void main(String[] args) {
        List intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}

