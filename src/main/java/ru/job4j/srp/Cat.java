package ru.job4j.srp;

public class Cat {
    private String name;
    private double weight;
    private int foodBowlLevel;

    public Cat(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    //Ответственность 1 действие кота
    public void eat() {
        if (foodBowlLevel >= 10) {
            weight += 0.1;
            foodBowlLevel -= 10;
            System.out.println(name + " is eating...");
        } else {
            System.out.println("Bowl is empty!");
        }
    }

    //Ответственность 2 управление миской
    public void refillBowl(int amount) {
        foodBowlLevel += amount;
        System.out.println("Bowl refilled. Now: " + foodBowlLevel);
    }
}
