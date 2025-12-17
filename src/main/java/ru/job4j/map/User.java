package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Integer.hashCode(children);
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User user) {
            if (this.birthday.equals(user.birthday) && this.children == user.children && this.name.equals(user.name)) {
                return true;
            }
        }
        return false;
    }

    public User(int birthdayYear, int birthdayMonth, int birthdayDay, int children, String name) {
        this.birthday = new GregorianCalendar(birthdayYear, birthdayMonth, birthdayDay);
        this.children = children;
        this.name = name;

    }
}



