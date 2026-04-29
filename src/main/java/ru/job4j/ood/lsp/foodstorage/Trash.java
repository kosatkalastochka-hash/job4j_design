package ru.job4j.ood.lsp.foodstorage;

import java.util.function.Predicate;

public class Trash extends AbstractStore {
    public Trash(Predicate<Double> predicate) {
        super(predicate);
    }
}
