package ru.job4j.io;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    ArgsName names = new ArgsName();

    public void packFiles(List<Path> sources, File target) {
        Path source = Paths.get(names.get("d"));
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            sources.forEach(path -> {
                        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                            String intermediate = (source.relativize(path)).toString();
                            zip.putNextEntry(new ZipEntry(intermediate));
                            zip.write(input.readAllBytes());
                            zip.closeEntry();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(String[] args) {
        names.parse(args);
        Map<String, String> values = names.getValues();
        File sources = Paths.get(names.get("d")).toFile();
        File target = Paths.get(names.get("o")).toFile();
        if (values.size() != 3) {
            throw new IllegalArgumentException(String.format("Error: "
                    + "Invalid number of arguments specified, passed out %s of 3", values.size()));
        } else if (!sources.exists()) {
            throw new IllegalArgumentException("Directory does not exist");
        } else if (!target.getName().endsWith(".zip")) {
            throw new IllegalArgumentException("The directory extension is incorrect, the correct extension is \".zip\"");
        }
    }

    private List<Path> getSources() {
        Path source = Paths.get(names.get("d"));
        try (Stream<Path> paths = Files.walk(source)) {
            return paths
                    .filter(path -> !path.toFile().isDirectory() && !path.toFile().getName().endsWith(names.get("e")))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void packSingleFile(File source, File target) {
        try (var zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
            zip.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validate(args);
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        File target = Paths.get(zip.names.get("o")).toFile();
        zip.packFiles(zip.getSources(), target);
    }
}