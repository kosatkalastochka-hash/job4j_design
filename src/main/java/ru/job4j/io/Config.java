package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private String check(String s) {
        if (s.trim().matches("^=.*|[^=]*=$|^[^=]*$")) {
            throw new IllegalArgumentException();
        }
        return s;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .filter(s -> !(s.isEmpty() || s.startsWith("#")))
                    .map(this::check)
                    .map(s -> s.split("=", 2))
                    .forEach(strings -> values.put(strings[0].trim(), strings[1].trim()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}