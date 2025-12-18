package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        File target = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(string -> result.append(String.format("%s ", string)));
        }
        assertThat("10:58:01 - 10:59:01 11:01:02 - 11:02:02 12:57:01 - 13:02:02 ").hasToString(result.toString());
    }
}

