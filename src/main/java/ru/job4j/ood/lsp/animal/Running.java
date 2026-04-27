package ru.job4j.ood.lsp.animal;

import java.util.HashSet;
import java.util.Set;

public class Running {
    public static void main(String[] args) {
        Meat meat = new Meat("Говядиина");
        Grass grass = new Grass("Листья цекропия");
        Lion lion = new Lion();
        Sloth sloth = new Sloth();
        Set<Animal> zoo = new HashSet<>();
        zoo.add(lion);
        zoo.add(sloth);
        // Нарушен принцип подстановки Лисков. Использование методов getClass(), instance of.
        for (Animal animal : zoo) {
            if (animal instanceof Lion) {
                animal.feed(meat);
            } else {
                animal.feed(grass);
            }
        }
    }
}
