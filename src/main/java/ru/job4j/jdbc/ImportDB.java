package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class ImportDB {
    private Properties config;
    private String dump;
    Pattern emailPattern = Pattern.compile(".+@.+\\..+");
    Pattern namePattern = Pattern.compile("^[a-zA-Zа-яА-Я\\s]{2,30}$");

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    private boolean valid(String[] people) {
        if (people.length >= 2) {
            boolean isValidEmail = namePattern.matcher(people[0]).matches();
            boolean isValidName = emailPattern.matcher(people[1]).matches();
            if (isValidEmail & isValidName) {
                return true;
            }
        }
        throw new IllegalArgumentException("Формат данных указан не верно");

    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .map(s -> s.split(";", 2))
                    .filter(this::valid)
                    .map(strings -> new User(strings[0], strings[1]))
                    .forEach(users::add);
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url_"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email) VALUES (?,?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "./data/dump.txt");
        dataBase.save(dataBase.load());
    }

}







