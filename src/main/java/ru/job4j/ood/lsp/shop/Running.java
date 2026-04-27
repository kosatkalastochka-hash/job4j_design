package ru.job4j.ood.lsp.shop;

public class Running {
    public static void main(String[] args) {
        RetailShop retail = new RetailShop();
        retail.add("book", 100);
        retail.addPromotional("pen", 20);
        retail.sale("book", 2, 200);

        WholesaleShop wholesale = new WholesaleShop();
        wholesale.sale("book", 2, 360);
    }


}
