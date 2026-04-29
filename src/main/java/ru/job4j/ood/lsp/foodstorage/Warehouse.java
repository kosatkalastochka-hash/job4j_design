package ru.job4j.ood.lsp.foodstorage;

import java.util.function.Predicate;

public class Warehouse extends AbstractStore {
    public Warehouse(Predicate<Double> predicate) {
        super(predicate);
    }
}

