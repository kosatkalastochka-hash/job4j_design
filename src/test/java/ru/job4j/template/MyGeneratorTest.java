package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class MyGeneratorTest {

    private final Generator generator = new MyGenerator();
    
    @Disabled
    @Test
    public void whenTemplateContainsKeysThatAreNotInTheMap() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Ivan",
                "age", "30"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenThereAreExtraKeysInTheMap() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Ivan",
                "subject", "Petrov",
                "age", "30"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTheArgumentsAreSpecifiedCorrectly() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Ivan",
                "subject", "Petrov"
        );
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("I am a Ivan, Who are Petrov? ");
    }

    @Test
    public void whenOneKeyIsUsedMultipleTimes() {
        String template = " ${name} is ${name} !";
        Map<String, String> args = Map.of("name", "Ivan");
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("Ivan is Ivan !");
    }

    @Test
    public void whenValueWithDollarAndBraces() {
        String template = "Price: ${price}";
        Map<String, String> args = Map.of("price", "${100}");
        String result = generator.produce(template,args);
        assertThat(result).isEqualTo("Price: ${100}");
    }

    @Test
    public void whenTheMapIsEmpty(){
        String template = "Price: ${price}";
        Map<String, String> args = Map.of();
        assertThatThrownBy(() -> generator.produce(template,args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenThereAreNoKeysInTheTemplate(){
        String template = "Hi all!";
        Map<String, String> args = Map.of("name", "Ivan");
        assertThatThrownBy(() -> generator.produce(template,args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testNullTemplate() {
        Map<String, String> args = Map.of("name", "Ivan");
        assertThatThrownBy(() -> generator.produce(null, args))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testNullArgs() {
        String template = "Hello, ${name}!";
        assertThatThrownBy(() -> generator.produce(template, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testNullValueInMap() {
        String template = "Hello, ${name}!";
        Map<String, String> args = new HashMap<>();
        args.put("name", null);
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}