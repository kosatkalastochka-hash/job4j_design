package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Student {
    private final String name;
    private final boolean served;
    private final int age;
    protected Contact contact;
    protected String[] books;

    public Student(int age, Contact contact, String name, boolean served, String[] books) {
        this.age = age;
        this.contact = contact;
        this.name = name;
        this.served = served;
        this.books = books;
    }

        @Override
        public String toString() {
            return "Student{"
                    + "age=" + age
                    + ", name='" + name + '\''
                + ", served=" + served
                + ", contact=" + contact
                + ", books=" + Arrays.toString(books)
                + '}';
    }

    public static void main(String[] args) {
        Student student = new Student(21,
                new Contact(4569, "+7-996-532-34-98"),
                "Андрей", true,
                new String[]{"Биология", "Геометрия", "Химия"});
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String studentGson = gson.toJson(student);
        System.out.println(studentGson);
        final Student studentMod = gson.fromJson(studentGson, Student.class);
        System.out.println(studentMod);
    }
}

