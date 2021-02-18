package com.company;

import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = new PersonalExecutor(2);

        Future<String> task = executorService.submit(() -> "First");
        // executorService.shutdown();

        if (task.isDone() && !task.isCancelled()) {
            System.out.println("Future result: " + task.get());
        }

        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Second");
            }
        });
        executorService.shutdown();

        try {
            executorService.submit(() -> "Test");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
