package ru.job4j.ood.isp.worker;

public class Robot implements Worker {
//класс Robot вынужден наследовать неиспользуемые методы
    @Override
    public void eat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sleep() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void work() {
        System.out.println("Робот работает");
    }
}
