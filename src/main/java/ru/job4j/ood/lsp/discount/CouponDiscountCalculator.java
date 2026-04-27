package ru.job4j.ood.lsp.discount;

public class CouponDiscountCalculator extends DiscountCalculator {
   /* Нарушение контракта класса родителя. Принципа LSP - постусловия не могут быть ослаблены в подклассе.
   В данном случае скидка не ограниченна 50%.
    */

    double calculate(double discountCoupon, double price) {
        if (price - discountCoupon < 0) {
            return 0;
        }
        return price - discountCoupon;
    }
}
