package ru.job4j.assertj;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void toArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] trees = simpleConvert.toArray("Сосна", "Бурёза", "Ель", "Тополь", "Ольха", "Осина");
        assertThat(trees).isNotEmpty()
                .containsAnyOf("Сосна", "Пальма", "Дуб")
                .endsWith("Ольха", "Осина")
                .allSatisfy(e -> assertThat(e).matches("^.+"));
    }

    @Test
    void toList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> names = simpleConvert.toList("Сергей", "Егор", "Виталий", "Наталья", "Виктория");
        assertThat(names).first().isEqualTo("Сергей");
        assertThat(names).isNotEmpty()
                .contains("Виталий")
                .doesNotContain("Жора")
                .allSatisfy(e -> assertThat(e).matches("^[А-Я][а-я]+"));
    }

    @Test
    void toSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> flowers = simpleConvert.toSet("Одуванчик", "Роза", "Тюльпан", "Ландыш", "Нарцис", "Ромашка");
        assertThat(flowers).isNotEmpty()
                .anyMatch(e -> e.equals("Роза"))
                .noneMatch(e -> e.equals("Тополь"))
                .anySatisfy(e -> assertThat(e).isEqualTo("Одуванчик"));
    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> numbers = simpleConvert.toMap("2", "2", "4", "6", "8", "10", "12");
        assertThat(numbers).hasSize(6)
                .containsKeys("2")
                .containsValues(6, 4)
                .containsEntry("2", 0);
    }
}