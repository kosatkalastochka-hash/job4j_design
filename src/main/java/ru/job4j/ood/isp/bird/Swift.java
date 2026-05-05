package ru.job4j.ood.isp.bird;

public class Swift implements Bird {

    @Override
    public void fluffUpFeathers() {
        System.out.println("Стриж распушил перья");
    }

    @Override
    public void fly() {
        System.out.println("Стриж полетел");
    }

    @Override
    public void go() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void layAnEgg() {
        System.out.println("Стриж отложил яйцо");
    }

    @Override
    public void swim() {
        throw new UnsupportedOperationException();
    }
}
