package ru.job4j.sort;

import java.util.Arrays;

public class Merge {
    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int capacity = left.length + right.length;
        int[] result = new int[capacity];
        int i = 0;
        int j = 0;
        int z = 0;
        for (; i < left.length && j < right.length; z++) {
            if (left[i] < right[j]) {
                result[z] = left[i++];
            } else {
                result[z] = right[j++];
            }
        }
        for (; i < left.length; i++) {
            result[z++] = left[i];
        }
        for (; j < right.length; j++) {
            result[z++] = right[j];
        }
        return result;
    }
}
