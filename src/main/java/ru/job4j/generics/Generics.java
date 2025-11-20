package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("животное"));
        second.add(new Predator("xищник"));
        third.add(new Tiger("тигр"));

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

//        generics.printBoundedWildCard(first);
//  Т.к. данный метод имеет ограничение сверху(данный класс и наследники) ограничиваться классом Predator,
//  под это ограничение не попадает Animal, являющийся супер классом.

        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
//        generics.printLowerBoundedWildCard(third);
//      Т.к. данный метод имеет ограничение снизу(данный класс и предки) ограничиваться классом Predator,
////  под это ограничение не попадает Tiger, являющийся наследником Predator.

    }

    public void printObject(List<? extends Animal> list) {
        for (Iterator<? extends Animal> iterator = list.iterator(); iterator.hasNext();) {
            Animal next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

}
