package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream(".\\data\\even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Arrays.stream(text.toString().split(System.lineSeparator()))
                .map(Integer::valueOf)
                .filter(integer -> integer % 2 == 0)
                .forEach(System.out::println);
    }
}
