package ru.job4j.io;

import java.io.*;

public class ByteArrayStream {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] bates1 = str.getBytes();
        ByteArrayInputStream stream1 = new ByteArrayInputStream(bates1, 3, 4);
        while ((data = stream1.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        String str1 = "Message";
        byte[] bytes2 = str1.getBytes();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.writeBytes(bytes2);
        System.out.println(out);
        byte[] byteArray = out.toByteArray();
        try (FileOutputStream fileout = new FileOutputStream(".\\src\\data\\message.txt")) {
            out.writeTo(fileout);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}