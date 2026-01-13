package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String string = input.readLine();
                    System.out.println(string);
                    if (string.matches("^(?!GET /\\?msg=).*") || string.split("=", 2)[2].trim().equals("HTTP/1.1")) {
                        output.println("HTTP/1.1 400 Bad Request");
                        output.println();
                        output.println("Invalid request, please enter your request after http://localhost:9000/?msg=");
                        continue;
                    }
                    output.println("HTTP/1.1 200 OK");
                    output.println();
                    String value = (string.split("=", 2))[1].trim();
                    String message = value.substring(0, value.length() - 9);
                    if (message.equals("Exit")) {
                        server.close();
                    } else {
                        output.println(message);
                    }
                }
            }
        }
    }
}

