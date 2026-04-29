package ru.job4j.ood.lsp.foodstorage;

import java.util.List;

public interface Store {
    boolean add(Food food);

    void deleteAll();

    List<Food> getFoods();
}
