package com.gmail.at.kotamadeo.server;

import com.gmail.at.kotamadeo.util.FibonacciSequenceGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server implements Runnable {
    private AtomicBoolean serverStart = new AtomicBoolean();

    public Server() {
        serverStart.set(false);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            serverStart.set(true);
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while (true) {
                    String line;
                    int input;
                    BigInteger fibonacciNumber;
                    while ((line = in.readLine()) != null) {
                        if (line.equals("exit")) {
                            return;
                        }
                        try {
                            input = Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            out.println("Неверный запрос!");
                            continue;
                        }
                        fibonacciNumber = FibonacciSequenceGenerator.getNValue(input);
                        if (fibonacciNumber == null) {
                            out.println("Неверный запрос!");
                            continue;
                        }
                        out.printf("Последовательность Фибоначчи для числа '%d' - %s.%n", input, fibonacciNumber);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AtomicBoolean isServerStart() {
        return serverStart;
    }
}
