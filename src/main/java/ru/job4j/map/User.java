package ru.job4j.map;

import java.util.Calendar;

public class User {
   private String name;
   private int children;
   private Calendar birthday;

    public User(Calendar birthday, int children, String name) {
        this.birthday = birthday;
        this.children = children;
        this.name = name;
    }
}
