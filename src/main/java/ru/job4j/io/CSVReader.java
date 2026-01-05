package ru.job4j.io;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        PrintStream outputStream;
        if ("stdout".equals(out)) {
            outputStream = System.out;
        } else {
            outputStream = new PrintStream(out);
        }
        try (Scanner sc = new Scanner(Paths.get(path));
             PrintStream printStream = outputStream) {
            String header = sc.nextLine();
            List<Integer> indices = getIndices(header, delimiter, filter);

            Stream.concat(Stream.of(header), sc.useDelimiter(System.lineSeparator()).tokens())
                    .map(line -> line.split(delimiter))
                    .map(values ->
                            indices.stream()
                                    .map(index -> values[index])
                                    .toArray(String[]::new)
                    )
                    .map(values -> String.join(delimiter, values))
                    .forEach(printStream::println);
        }
    }

    private static List<Integer> getIndices(String headersLine, String delimiter, String filter) {
        String[] headers = headersLine.split(delimiter);
        Map<String, Integer> headerIndices = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            headerIndices.put(headers[i], i);
        }
        List<Integer> indices = new ArrayList<>();
        String[] filters = filter.split(",");
        for (String f : filters) {
            Integer index = headerIndices.get(f);
            if (index != null) {
                indices.add(index);
            }
        }
        return indices;
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}