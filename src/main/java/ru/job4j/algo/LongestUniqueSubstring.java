package ru.job4j.algo;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        int maximumLength = 0;
        int bestStart = 0;
        int left = 0;
        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);
            Integer lastIndex = map.get(currentChar);
            if (lastIndex != null && lastIndex >= left) {
                if ((right - (left + 1)) > maximumLength) {
                  maximumLength = (right - (left + 1));
                    bestStart = left;
                }
                left = lastIndex + 1;
            }
            map.put(currentChar, right);
        }

        return  maximumLength > str.length() - left ? str.substring(bestStart, bestStart + maximumLength)
                : str.substring(left);
    }
}