package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> pathSet = new HashSet<>();
    Set<Path> duplicatesSet = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (pathSet.contains(fileProperty)) {
            duplicatesSet.add(file.normalize().toAbsolutePath());
        } else {
            pathSet.add(fileProperty);
        }
        return FileVisitResult.CONTINUE;
    }

    public Set<Path> getDuplicatesSet() {
        return duplicatesSet;
    }
}
