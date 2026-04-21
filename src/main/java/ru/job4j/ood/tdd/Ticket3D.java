package ru.job4j.ood.tdd;

import java.util.Calendar;

public class Ticket3D implements Ticket {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ticket;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int getSeat() {
        return 0;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public Calendar getDate() {
        return null;
    }

    @Override
    public Cinema getCinema() {
        return null;
    }

    @Override
    public Account getAccount() {
        return null;
    }
}
