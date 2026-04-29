package ru.job4j.ood.lsp.foodstorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Running {
    public static void main(String[] args) {
        Shop shop = new Shop(part -> part <= 0.75 && part >= 0, part -> part < 0.25, 20);
        Warehouse warehouse = new Warehouse(part -> 0.75 < part);
        Trash trash = new Trash(part -> part < 0);

        Food rice = new Food("Рис", LocalDate.of(2025, 4, 1), LocalDate.of(2026, 1, 1), 0, 100);
        Food flour = new Food("Мука пшеничная", LocalDate.of(2026, 1, 5), LocalDate.of(2026, 12, 1), 0, 120);
        Food corn = new Food("Консервированая кукуруза", LocalDate.of(2026, 2, 5), LocalDate.of(2027, 1, 5), 0, 80);
        Food eggs = new Food("Куриные яйца", LocalDate.of(2026, 4, 3), LocalDate.of(2026, 5, 3), 0, 120);

        List<Food> products = new ArrayList<>();
        products.add(rice);
        products.add(flour);
        products.add(corn);
        products.add(eggs);

        ControlQuality control = new ControlQuality(products, shop, warehouse, trash);
        List<Food> unsorted = control.sorting();
        System.out.printf("Перечень нераспределенных продуктов %s %n %n", unsorted);
        System.out.printf("Склад warehouse %s %n %n", warehouse.getFoods());
        System.out.printf("Склад shop %s %n %n", shop.getFoods());
        System.out.printf("Склад trash %s %n %n", trash.getFoods());
    }
}
