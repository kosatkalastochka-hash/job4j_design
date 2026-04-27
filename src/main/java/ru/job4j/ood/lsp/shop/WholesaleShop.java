package ru.job4j.ood.lsp.shop;

public class WholesaleShop extends Shop {
    public WholesaleShop() {
        super();
    }

    @Override
    void add(String productName, int price) {
        super.add(productName, price);
    }

    @Override
    void sale(String productName, double count, int money) {
        if (count >= 10) {
            count = count * 0.9;
            //Нарушение принципа lsp. Предусловия (Preconditions) не могут быть усилены в подклассе.
            super.sale(productName, count, money);
        }
        System.out.println("У нас оптовый магазин - реализация минимум с 10 едеиниц. "
                + "В таком количестве вы можете приобрести товар в наших розничных магазинах.");
    }
}
