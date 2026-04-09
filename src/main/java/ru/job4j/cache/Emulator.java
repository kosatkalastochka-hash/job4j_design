package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    private static DirFileCache cache;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.println("1. Указать кэшируемую директорию");
                System.out.println("2. Загрузить содержимое файла в кэш");
                System.out.println("3. Получить содержимое файла из кэша");
                System.out.println("4. Выход");
                System.out.print("Выберите опцию: ");
                int choice = ValidationUtils.validateInt(scanner);

                switch (choice) {
                    case 1:
                        System.out.print("Введите путь к кэшируемой директории: ");
                        String dir = ValidationUtils.validatePath(scanner);
                        cache = new DirFileCache(dir);
                        break;
                    case 2:
                        if (cache == null) {
                            System.out.println("Сначала укажите кэшируемую директорию.");
                            break;
                        }
                        System.out.print("Введите имя файла для загрузки в кэш: ");
                        String fileToLoad = ValidationUtils.isFileExistsInDirectory(scanner, cache.getCachingDir());
                        try {
                            cache.get(fileToLoad);
                            System.out.println("Файл загружен в кэш.");
                        } catch (RuntimeException e) {
                            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
                        }
                        break;
                    case 3:
                        if (cache == null) {
                            System.out.println("Сначала укажите кэшируемую директорию.");
                            break;
                        }
                        System.out.print("Введите имя файла для получения из кэша: ");
                        String fileToGet = ValidationUtils.isFileExistsInDirectory(scanner, cache.getCachingDir());
                        try {
                            String content = cache.get(fileToGet);
                            System.out.println("Содержимое файла: " + content);
                        } catch (RuntimeException e) {
                            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
                        }
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                }
            }
        }
    }
}