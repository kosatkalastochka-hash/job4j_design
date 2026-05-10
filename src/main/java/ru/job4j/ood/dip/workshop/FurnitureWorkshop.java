package ru.job4j.ood.dip.workshop;

public class FurnitureWorkshop {

    public Chair makeChair(String name, Double price) {
        System.out.println("Стул изготовлен");
        return new Chair(name, price);
    }

    public Table makeTable(String name, Double price) {
        System.out.println("Стул изготовлен");
        return new Table(name, price);
    }
}
/*
Логика изготовления будет повторяется в каждом методе.
Высокая связанность — метод мастерской зависит от конкретной мебели
Нарушение DIP
Нужен интерфейс Furniture
 Метод FurnitureWorkshop должен принимать тип изготавливаемой медели
  и в соответствии с ним изготавливать мебель, возвращая объект Furniture
 К полученному объекту можно будет приминить общие для интерфейса Furniture методы.
* */