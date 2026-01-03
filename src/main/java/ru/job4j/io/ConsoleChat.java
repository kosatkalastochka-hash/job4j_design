package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Pattern p1 = Pattern.compile(STOP, CASE_INSENSITIVE | UNICODE_CHARACTER_CLASS);
        Pattern p2 = Pattern.compile(CONTINUE, CASE_INSENSITIVE | UNICODE_CHARACTER_CLASS);
        Pattern p3 = Pattern.compile(OUT, CASE_INSENSITIVE | UNICODE_CHARACTER_CLASS);
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        boolean flag = true;
        Random random = new Random();
        try (sc) {
            String choice = "";
            while (!p3.matcher(choice).matches()) {
                String line = sc.nextLine();
                log.add(line);
                choice = line.trim();
                switch (choice) {
                    case String s when p1.matcher(s).matches() -> flag = false;
                    case String s when p2.matcher(s).matches() -> flag = true;
                    default -> {
                        if (flag && !p3.matcher(choice).matches()) {
                            int index = random.nextInt(phrases.size());
                            String answer = phrases.get(index);
                            log.add(answer);
                            System.out.println(answer);
                        }
                    }

                }
            }
            saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        File file1 = Path.of(String.format("./data/%s", botAnswers)).toFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1, StandardCharsets.UTF_8))) {
            reader.lines()
                    .map(String::trim)
                    .filter(string -> !string.isEmpty())
                    .forEach(answers::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        File file2 = Path.of(String.format("./data/%s", path)).toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2, StandardCharsets.UTF_8))) {
            for (String s : log) {
                writer.write(String.format("%s %n", s));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("path.txt", "botAnswers.txt");
        consoleChat.run();
    }
}