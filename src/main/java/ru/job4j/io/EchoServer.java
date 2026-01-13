package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class EchoServer {
    private  static  final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String string = input.readLine();
                    System.out.println(string);

                    if (string.matches("^(?!GET /\\?msg=).*") || "HTTP/1.1".equals(string.split("=", 2)[2].trim())) {
                        output.println("HTTP/1.1 400 Bad Request");
                        output.println();
                        output.println("Invalid request, please enter your request after http://localhost:9000/?msg=");
                        continue;
                    }
                    output.println("HTTP/1.1 200 OK");
                    output.println();
                    String value = (string.split("=", 2))[1].trim();
                    String message = value.substring(0, value.length() - 9);
                    if ("Exit".equals(message)) {
                        server.close();
                    } else {
                        output.println(message);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Соединение потеряно или отклонено во время обмена данными по сети", e);
        }
    }
}

