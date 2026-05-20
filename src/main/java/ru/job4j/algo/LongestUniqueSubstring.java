package ru.job4j.algo;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        String maxString = "";
        char[] chars = str.toCharArray();
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            char currentChar = chars[right];
            Integer lastIndex = map.get(currentChar);
            if (lastIndex != null && lastIndex >= left) {
                String currentSubstring = str.substring(left, right);
                maxString = currentSubstring.length() > maxString.length() ? currentSubstring : maxString;
                left = lastIndex + 1;
            }
            map.put(currentChar, right);
        }
        String lastString = str.substring(left, str.length());

        return lastString.length() > maxString.length() ? lastString : maxString;
    }
}