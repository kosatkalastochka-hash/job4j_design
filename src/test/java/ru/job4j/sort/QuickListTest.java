package ru.job4j.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class QuickListTest {
    User user1 = new User(1, "11");
    User user2 = new User(1, "22");
    User user3 = new User(3, "33");
    User user4 = new User(-4, "44");
    User user5 = new User(4, "55");
    User user6 = new User(6, "66");

    @Test
    void whenSortThenOk() {
        Comparator<User> comparator = Comparator.comparingInt(User::getId);
        List<User> listUser = new ArrayList<>();
        listUser.add(user3);
        listUser.add(user5);
        listUser.add(user1);
        listUser.add(user4);
        listUser.add(user6);
        listUser.add(user2);
        QuickList.quickSort(listUser, comparator);
        List<Integer> result = listUser.stream().map(User::getId).toList();
        assertThat(result).containsExactly(-4, 1, 1, 3, 4, 6);
    }

    @Test
    void whenReverseOrderSortUserName() {
        Comparator<User> comparator = Comparator.comparing(User::getName);
        List<User> list = new ArrayList<>();
        list.add(user3);
        list.add(user5);
        list.add(user1);
        list.add(user4);
        list.add(user6);
        list.add(user2);
        QuickList.quickSort(list, comparator);
        List<User> result = new ArrayList<>(list);
        result.sort(comparator);
        boolean equals = result.equals(list);
        assertThat(equals).isTrue();
    }

    @Test
    void whenReverseOrderThenOk() {
        Comparator<Integer> comparator = (a, b) -> b - a;
        List<Integer> list = new ArrayList<>();
        list.add(-2);
        list.add(-8);
        list.add(1);
        list.add(0);
        list.add(10);
        list.add(1);
        list.add(8);
        QuickList.quickSort(list, comparator);
        System.out.println(list);
        assertThat(list).containsExactly(10, 8, 1, 1, 0, -2, -8);
    }

    @Test
    void whenTypeStringNaturalOrder() {
        Comparator<String> comparator = Comparator.naturalOrder();
        List<String> list = new ArrayList<>();
        list.add("Антена");
        list.add("Тумба");
        list.add("Доска");
        list.add("Гарнитур");
        list.add("Диван");
        list.add("Стул");
        list.add("Кровать");
        QuickList.quickSort(list, comparator);
        List<String> result = new ArrayList<>(list);
        result.sort(comparator);
        boolean equals = result.equals(list);
        assertThat(equals).isTrue();
    }

    @Test
    void whenTypeСharReverseOrder() {
        Comparator<String> comparator = Comparator.reverseOrder();
        List<String> list = new ArrayList<>();
        list.add("А");
        list.add("Т");
        list.add("Д");
        list.add("Г");
        list.add("Д");
        list.add("Е");
        list.add("Ё");
        QuickList.quickSort(list, comparator);
        List<String> result = new ArrayList<>(list);
        result.sort(comparator);
        boolean equals = result.equals(list);
        assertThat(equals).isTrue();
    }


    private static class User {
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}