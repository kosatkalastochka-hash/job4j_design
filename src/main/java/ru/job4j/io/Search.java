package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private static Path start;

    public static void main(String[] args) throws IOException {
        validate(args);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Неверное количество аргументов!. "
                    + "Укажите 2 аргумента :директорию в котрой осуществляется поиск; "
                    + "фильтр - символы которыми заканчивается название файла!");
        }
        start = Path.of(args[0]);
        File file = start.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
