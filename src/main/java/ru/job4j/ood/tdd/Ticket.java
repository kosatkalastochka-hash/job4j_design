package ru.job4j.ood.tdd;

import java.util.Calendar;

public interface Ticket {
    int getRow();

    int getSeat();

    Calendar getDate();

    Account getAccount();

    Cinema getCinema();
}
