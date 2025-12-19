package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Redmi\\IdeaProjects\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        } else if (file.exists()) {
            System.out.printf("Имя файла: %s %n", file.getName());
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.printf("Размер директории: %s%n", file.getTotalSpace());
        for (File subfile : file.listFiles()) {
            System.out.printf("Имя файла: %s; Размер файла: %s; %n", subfile.getName(), subfile.length());
        }
    }
}
