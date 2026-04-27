package ru.job4j.ood.lsp.animal;

public class Grass implements Eatable {
    public Grass(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String getName() {
        return name;
    }
}
