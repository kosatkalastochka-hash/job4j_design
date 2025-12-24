package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Path> pathMap = new HashMap<>();
    private final Map<FileProperty, Set<Path>> duplicatesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes)  {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (pathMap.containsKey(fileProperty)) {
            Set<Path> set = new HashSet<>();
            set.add(pathMap.get(fileProperty).normalize().toAbsolutePath());
            set.add(file.normalize().toAbsolutePath());
            duplicatesMap.put(fileProperty, set);
        } else {
            pathMap.put(fileProperty, file.normalize().toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<FileProperty, Set<Path>> getDuplicatesMap() {
        return duplicatesMap;
    }

}
