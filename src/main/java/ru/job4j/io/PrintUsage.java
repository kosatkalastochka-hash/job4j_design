package ru.job4j.io;

import java.io.*;

public class PrintUsage {

    public static void main(String[] args) {
        File file = new File(".\\src\\data\\print.txt");
        try (PrintStream stream = new PrintStream(new FileOutputStream(file));
             PrintWriter writer = new PrintWriter(file)) {
            stream.println("Из PrintStream в FileOutputStream");
            stream.write("Новая строка".getBytes());
            writer.println("Новое сообщение");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
