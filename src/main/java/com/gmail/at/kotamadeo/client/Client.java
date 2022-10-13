package com.gmail.at.kotamadeo.client;

import com.gmail.at.kotamadeo.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Server server = new Server();
        Thread thread = new Thread(server);
        thread.start();
        while (!server.isServerStart().get()) {
            try (Socket socket = new Socket("127.0.0.1", 8080);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 Scanner scanner = new Scanner(System.in)) {
                String msg;
                while (true) {
                    System.out.print("Введите целое число в диапазоне: 0 - 2147483647 или exit: ");
                    msg = scanner.nextLine();
                    out.println(msg);
                    if ("exit".equals(msg)) {
                        break;
                    }
                    System.out.println("Сервер: " + in.readLine() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}