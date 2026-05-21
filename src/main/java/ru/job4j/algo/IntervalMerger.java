package ru.job4j.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalMerger {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(row -> row[0]));

        int[] current = intervals[0];
        list.add(current);

        for (int i = 1; i < intervals.length; i++) {
            if (current[1] >= intervals[i][0]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                current = intervals[i];
                list.add(current);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
