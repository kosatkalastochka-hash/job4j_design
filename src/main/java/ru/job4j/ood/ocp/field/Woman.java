package ru.job4j.ood.ocp.field;

import java.util.List;

public class Woman {
    int age;
    String name;
List<Sportcar>  carPark;
// Нарушение принципа OCP указание типа - Sportcar ограничивает нас в подборе машины. Если у
// леди поменяются вкусы и она захочет приобрести минивен, нам прийдется переписывать весь код класса Woman.
// Заменять тип Sportcar на Car.
// Верным решением будет изначально указать интерфейс, оставив возможность для расширения.
}
