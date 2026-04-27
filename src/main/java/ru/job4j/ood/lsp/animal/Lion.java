package ru.job4j.ood.lsp.animal;

public class Lion implements Animal {

    @Override
    public void feed(Eatable food) {
        if (!(food instanceof Meat)) {
            throw new IllegalArgumentException("Лев ест только мясо!");
        }
        System.out.printf("Лев съел %s %n", food.getName());
    }
}
