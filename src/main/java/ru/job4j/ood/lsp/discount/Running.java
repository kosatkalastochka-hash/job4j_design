package ru.job4j.ood.lsp.discount;

public class Running {
    public static void main(String[] args) {
        DiscountCalculator calculator = new DiscountCalculator();
        Double discountPrice = calculator.calculate(120, 100);
        System.out.printf(" Цена со скидкой %s %n", discountPrice);

        CouponDiscountCalculator couponDiscountCalculator = new CouponDiscountCalculator();
        Double couponDiscountPrice = couponDiscountCalculator.calculate(120, 100);
        System.out.printf(" Цена со скидкой по купону %s %n", couponDiscountPrice);
    }
}
