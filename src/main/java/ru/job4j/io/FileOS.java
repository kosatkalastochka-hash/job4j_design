package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOS {
    public static void main(String[] args) {
        int max = 10;
        try (FileOutputStream output = new FileOutputStream(".\\data\\dataresult.txt")) {
            output.write("   |".getBytes());
            for (int j = 1; j <= max; j++) {
                output.write(String.format("%4d", j).getBytes());
            }
            output.write("\n---|----------------------------------------".getBytes());
            output.write(System.lineSeparator().getBytes());

            for (int i = 1; i <= max; i++) {
                output.write(String.format("%2d |", i).getBytes());
                for (int j = 1; j <= max; j++) {
                    output.write(String.format("%4d", i * j).getBytes());
                }
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
