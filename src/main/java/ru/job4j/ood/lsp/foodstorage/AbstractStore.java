package ru.job4j.ood.lsp.foodstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    private List<Food> foods = new ArrayList<>();
    Predicate<Double> predicate;

    public AbstractStore(Predicate<Double> predicate) {
        this.predicate = predicate;
    }

    public boolean add(Food food) {
        if (predicate.test(food.distribution())) {
            foods.add(food);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        foods.clear();
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }
}