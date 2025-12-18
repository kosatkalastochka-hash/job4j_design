package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String start = "";
            boolean isWorking = true;
            String[] lines = reader.lines().toArray(String[]::new);
            for (String line : lines) {
                String[] elements = line.split(" ");
                String code = elements[0];
                if (isWorking && ("400".equals(code) || "500".equals(code))) {
                    start = elements[1];
                    isWorking = false;
                } else if (!isWorking && ("200".equals(code) || "300".equals(code))) {
                    writer.write(String.format("%s - %s", start, elements[1]));
                    writer.write(System.lineSeparator());
                    isWorking = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
