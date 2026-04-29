package ru.job4j.ood.lsp.foodstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double discount, double price) {
        this.createDate = createDate;
        this.discount = discount;
        this.expiryDate = expiryDate;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(System.lineSeparator());
        sb.append("Food{");
        sb.append(System.lineSeparator());
        sb.append("name='").append(name).append('\'');
        sb.append(System.lineSeparator());
        sb.append("createDate=").append(createDate);
        sb.append(System.lineSeparator());
        sb.append("expiryDate=").append(expiryDate);
        sb.append(System.lineSeparator());
        sb.append("price=").append(price);
        sb.append(System.lineSeparator());
        sb.append("discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price * (1 - discount / 100);
    }

    protected double distribution() {
        long timeValid = ChronoUnit.DAYS.between(createDate, expiryDate);
        long timeValidNow = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
        return (double) timeValidNow / timeValid;
    }
}
