package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

import static java.sql.DriverManager.getConnection;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            String url = properties.getProperty("hibernate.connection.url");
            String login = properties.getProperty("hibernate.connection.username");
            String password = properties.getProperty("hibernate.connection.password");
            connection = getConnection(url, login, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void initStatement(String sgl) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sgl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s ();",
                tableName);
        initStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s ;",
                tableName);
        initStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;",
                tableName, columnName, type);
        initStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN IF EXISTS %s;",
                tableName, columnName);
        initStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName);
        initStatement(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void existTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = '%s')",
                    tableName
            );
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                boolean tableExists = resultSet.getBoolean(1);
                System.out.printf("Таблица '%s' %s", tableName, (tableExists ? "существует" : "не существует"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = new TableEditor(new Properties())) {
            tableEditor.createTable("Огород");
            System.out.println(tableEditor.getTableScheme("Огород"));
            tableEditor.addColumn("Огород", "Томаты", "TEXT");
            System.out.println(tableEditor.getTableScheme("Огород"));
            tableEditor.renameColumn("Огород", "Томаты", "Помидоры");
            System.out.println(tableEditor.getTableScheme("Огород"));
            tableEditor.dropColumn("Огород", "Помидоры");
            System.out.println(tableEditor.getTableScheme("Огород"));
            tableEditor.dropTable("Огород");
            tableEditor.existTable("Огород");
        }
    }
}