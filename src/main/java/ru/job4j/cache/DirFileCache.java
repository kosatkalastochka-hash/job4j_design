package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    protected DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    protected String getCachingDir() {
        return cachingDir;
    }

    @Override
    protected String load(String key) {
        try (Stream<String> results = Files.lines(Path.of(cachingDir, key))) {
            return results.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}