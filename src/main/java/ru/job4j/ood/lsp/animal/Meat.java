package ru.job4j.ood.lsp.animal;

public class Meat implements Eatable {
    String name;

    public Meat(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
