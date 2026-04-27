package ru.job4j.ood.lsp.discount;

public class DiscountCalculator {

    // Контракт запрещает делать скидку на товар больше 50%.

    double calculate(double discount, double price) {
        double discountPrice = price - discount;
        if (discountPrice > price * 0.5) {
            return discountPrice;
        }
        return price * 0.5;
    }
}
