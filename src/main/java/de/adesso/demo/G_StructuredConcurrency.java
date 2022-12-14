package de.adesso.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;

public class G_StructuredConcurrency {
    public static void main(String[] args) throws InterruptedException {

        try (var scope = new StructuredTaskScope<Void>()) {
            var failingTask = scope.fork(() -> {
                Thread.sleep(100);
                System.out.println("World");
                throw new RuntimeException("Test");
            });

            scope.fork(() -> {
                System.out.println("Hello");
                return null;
            });

            scope.join();           // Join both forks
        }
    }
}
