package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    /** Добавьте поля класса здесь, если это необходимо */
    public static int[] findSmallestRange(int[] nums, int k) {
        int left = 0;
        int[] result = new int[2];
        int counter = 1;

        for (int right = 1; right < nums.length; right++) {
            int current = nums[right];
            int prev = nums[left++];
            counter++;

            if (current == prev) {
                counter = 1;
                left = right;
            }
            if (counter == k) {
                result[0] = right + 1 - counter;
                result[1] = right;
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
