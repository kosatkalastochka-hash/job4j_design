package ru.job4j.question;

public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            User user = (User) obj;
            return this.id == user.id;
        }
    }

    public String getName() {
        return name;
    }
}