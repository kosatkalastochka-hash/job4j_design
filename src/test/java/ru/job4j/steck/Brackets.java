package ru.job4j.steck;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        Stack<Character> filter = new Stack();

        for (Character character : s.toCharArray()) {
            if ('(' == character || '{' == character || '[' == character) {
                filter.push(character);
            } else{
                if (filter.empty()) {
                    return false;
                }
                char elementFilter = filter.pop();
                if ((')' == character && (elementFilter != '(')
                        || '}' == character && (elementFilter != '{')
                        || ']' == character && (elementFilter != '['))) {
                    return false;
                }
            }
        }
        return filter.empty();
    }
}