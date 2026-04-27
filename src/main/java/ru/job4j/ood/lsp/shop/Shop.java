package ru.job4j.ood.lsp.shop;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    Map<String, Integer> product = new HashMap<>();

    // Базовый класс гарантирует, что продает продукты начиная с 1 шт.

    void sale(String productName, double count, int money) {
        if (count > 0) {
            double cost = product.get(productName) * count;
            System.out.printf("С вас %s  рублей", cost);
            if (money == cost) {
                System.out.println("Спасибо за покупку!");
            }
        }
    }

    void add(String productName, int price) {
        product.put(productName, price);
    }
}
