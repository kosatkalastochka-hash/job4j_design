package ru.job4j.io.serialization;

import jakarta.xml.bind.JAXBException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class Student {

    String name;
    boolean served;
    int age;
    Contact contact;
    String[] books;

    public Student() {
    }

    public Student(int age, Contact contact, String name, boolean served, String[] books) {
        this.age = age;
        this.contact = contact;
        this.name = name;
        this.served = served;
        this.books = books;
    }

    public int getAge() {
        return age;
    }

    public String[] getBooks() {
        return books;
    }

    public Contact getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public boolean isServed() {
        return served;
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

    public static void main(String[] args) throws JAXBException, IOException {
        Student student = new Student(21,
                new Contact(4569, "+7-996-532-34-98"),
                "Андрей", true,
                new String[]{"Биология", "Геометрия", "Химия"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", student.getAge());
        jsonObject.put("contact", student.getContact());
        jsonObject.put("name", student.getName());
        jsonObject.put("served", student.isServed());
        jsonObject.put("books", student.getBooks());
        System.out.println(jsonObject.toString());

        JSONObject jsonObject1 = new JSONObject(student);
        System.out.println(jsonObject1.toString());
    }
}

