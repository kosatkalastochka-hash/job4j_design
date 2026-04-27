package ru.job4j.ood.lsp.shop;

import java.util.HashMap;
import java.util.Map;

public class RetailShop extends Shop {
    Map<String, Integer> promotionalProducts = new HashMap<>();

    public RetailShop() {
        super();
    }

    @Override
    void add(String productName, int price) {
        super.add(productName, price);
    }

    @Override
    void sale(String productName, double count, int money) {
        super.sale(productName, count, money);
        System.out.println("Желаете преобрести у нас продукты по невероятно выгодной цене?");
        promotionalProducts.forEach((key, value) -> System.out.printf("%s - %d  рублей %n", key, value));
    }

    void addPromotional(String productName, int price) {
        promotionalProducts.put(productName, price);
    }
}
