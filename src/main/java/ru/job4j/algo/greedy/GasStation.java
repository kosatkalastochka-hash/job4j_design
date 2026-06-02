package ru.job4j.algo.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        for (int i = 0; i < start; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                return -1;
            }
        }
        return start;
    }
}
