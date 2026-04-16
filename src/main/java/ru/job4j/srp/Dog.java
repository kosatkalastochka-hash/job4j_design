package ru.job4j.srp;

public class Dog {
    String name;
    private int energy = 100;
    private int happiness = 50;
    boolean pawsClean = false;

    public Dog(int energy, int happiness, String name, boolean pawsClean) {
        this.energy = energy;
        this.happiness = happiness;
        this.name = name;
        this.pawsClean = pawsClean;
    }

    //Два действия в одном методе
    public void walk() {
        System.out.printf("%s,идет гулять", name);
        energy -= 20;
        happiness += 30;
        System.out.printf("%s,погулял:энергии %s, счастья %s", name, energy, happiness);
        System.out.printf("%s, моет лапы", name);
        pawsClean = true;
    }
}
