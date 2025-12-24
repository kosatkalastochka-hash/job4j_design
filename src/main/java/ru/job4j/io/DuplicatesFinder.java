package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicate = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicate);
        duplicate.getDuplicatesMap().forEach((fileProperty, paths) -> {
            System.out.println(fileProperty);
            paths.forEach((System.out::println));
        });
    }
}
