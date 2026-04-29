package ru.job4j.ood.lsp.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    List<Store> stores;
    List<Food> products;

    public ControlQuality(List<Food> products, Store... stores) {
        this.stores = List.of(stores);
        this.products = new ArrayList<>();
        if (products != null) {
            this.products.addAll(products);
        }
    }

    public List<Food> sorting() {
        List<Food> unsorted = new ArrayList<>();
        stores.forEach(store -> {
            products.addAll(store.getFoods());
            store.deleteAll();
        });

        for (Food product : products) {
            boolean addedProduct = false;
            for (Store store : stores) {
                if (store.add(product)) {
                    addedProduct = true;
                    break;
                }
            }
            if (!addedProduct) {
                unsorted.add(product);
            }
        }
        products.clear();
        System.out.printf("%d продуктов остались нераспределенными %n", unsorted.size());
        return unsorted;
    }
}

