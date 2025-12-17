package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.trim().matches("^[аеёиоуыэюяАЕЁИОУЫЭЮЯ].*")) {
                    strings.add(str);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }

    public void saveTo(String out) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            filter().forEach(str -> {
                try {
                    writer.write(str);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new LogFilter(".\\src\\data\\log.txt").saveTo(".\\src\\data\\404.txt");
    }
}
