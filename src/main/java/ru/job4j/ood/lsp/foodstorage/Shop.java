package ru.job4j.ood.lsp.foodstorage;

import java.util.function.Predicate;

public class Shop extends AbstractStore {
    private Predicate<Double> discountCondition;
    private double discount;

    public Shop(Predicate<Double> predicate, Predicate<Double> discountCondition, double discount) {
        super(predicate);
        this.discount = discount;
        this.discountCondition = discountCondition;
    }

    @Override
    public boolean add(Food food) {
        if (super.add(food)) {
            if (discountCondition.test(food.calculateRemainingShelfLife())) {
                food.setDiscount(discount);
            }
            return true;
        }
        return false;
    }
}
