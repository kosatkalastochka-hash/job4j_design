package ru.job4j.ood.isp.bird;

public class Penguin implements Bird {
    @Override
    public void fluffUpFeathers() {
        System.out.println("Пингвин распушил перья");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void go() {
        System.out.println("Пингвин идет");
    }

    @Override
    public void layAnEgg() {
        System.out.println("Пингвин откладывает яйцо");
    }

    @Override
    public void swim() {
        System.out.println("Пингвин плывет");
    }
}
