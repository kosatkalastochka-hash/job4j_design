package ru.job4j.ood.lsp.animal;

public class Sloth implements Animal {

    @Override
    public void feed(Eatable food) {
        if (!(food instanceof Grass)) {
            throw new IllegalArgumentException("Ленивец ест только листья!");
        }
        System.out.printf("Ленивец съел %s %n", food.getName());
    }
}
