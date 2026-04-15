package ru.job4j.ood.tdd;

public class Ticket3D implements Ticket {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ticket;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
