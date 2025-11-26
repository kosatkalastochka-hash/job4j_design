package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(int birthdayYear, int birthdayMonth, int birthdayDay, int children, String name) {
        this.birthday = new GregorianCalendar(birthdayYear, birthdayMonth, birthdayDay);
        this.children = children;
        this.name = name;
    }
}
