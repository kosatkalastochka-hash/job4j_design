package ru.job4j.io.serialization;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    String name;
    @XmlAttribute
    boolean served;
    @XmlAttribute
    int age;
    @XmlElement(name = "contact")
    Contact contact;
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
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
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Student result = (Student) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

