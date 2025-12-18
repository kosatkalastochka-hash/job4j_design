package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenWithCommentAndEmpty() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver=");
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=1");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenWithEqualsInEnd() {
        String path = "./data/test1.properties";
        Config config = new Config(path);
       assertThatThrownBy(config::load)
               .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWithEqualsInStart() {
        String path = "./data/test2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWithNotEquals() {
        String path = "./data/test3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOnlyEquals() {
        String path = "./data/test4.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}