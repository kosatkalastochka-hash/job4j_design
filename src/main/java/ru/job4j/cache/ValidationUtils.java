package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ValidationUtils {

    protected static int validateInt(Scanner sc) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= 1 && value <= 4) {
                    valid = true;
                } else {
                    System.out.println("Ошибка: число должно быть от 1 до 4");
                }
            } else {
                System.out.println("Ошибка: введите целое число!");
            }
            sc.nextLine();
        }
        return value;
    }

    protected static String validatePath(Scanner sc) {
        while (true) {
           String path = sc.nextLine().trim();
            if (path.isEmpty()) {
                System.out.println("Путь не может быть пустым! Повторите ввод:");
                continue;
            }
            try {
                Path folderPath = Paths.get(path);
                if (!Files.exists(folderPath)) {
                    System.out.printf("Директории,файла - %s не существует! Укажите путь к существующему файлу:", path);
                    continue;
                }
                if (!Files.isDirectory(folderPath)) {
                    System.out.println("Это файл, а не директория! Укажите путь к папке:");
                    continue;
                }
                return folderPath.toAbsolutePath().normalize().toString();
            } catch (InvalidPathException e) {
                System.out.printf("Путь к папке - %s указан не верно! Пример: C:\\\\Folder или /home/folder%n", path);
            }
        }
    }

    protected static String isFileExistsInDirectory(Scanner sc, String directory) {
        boolean valid = false;
        String fileName = "";
        while (!valid) {
            fileName = sc.nextLine();
            if (fileName.isEmpty()) {
                System.out.println("Имя файла не может быть пустым! Повторите ввод:");
                continue;
            }
            try {
                Path filePath = Path.of(directory, fileName);
                if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                    valid = true;
                }
            } catch (InvalidPathException e) {
                System.out.println("Имя файла указано не верно! Повторите ввод:");
            }
        }
        return fileName;
    }
}

