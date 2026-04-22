package ru.job4j.ood.ocp.inheritance;

public class Tigor implements Dog {
    // Нарушение наследования. Тигр не относится к собакам, поэтому не может наследоваться от данного интерфейса.

    @Override
    public void run() {
        System.out.println("Бежит со скоростью 40 км в час ");
    }

    @Override
    public void voice() {
        System.out.println("Ppppp");
    }

    @Override
    public void walkWithTheOwner() {
        System.out.println("Тигр съел хозяина ):");
    }
}
