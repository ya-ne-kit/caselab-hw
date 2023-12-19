package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            System.out.println("Асинхронный привет!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Все плохо");
            }
            System.out.println("Асинхронный пока!");
        }, 0, 10, TimeUnit.SECONDS);

        while (true) {
            System.out.println("Работает основная программа");
            Thread.sleep(1000);
        }
    }
}
