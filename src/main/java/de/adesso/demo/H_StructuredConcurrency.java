package de.adesso.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;

public class H_StructuredConcurrency {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(() -> {
                Thread.sleep(100);
                System.out.println("World");
                return null;
            });

            scope.fork(() -> {
                System.out.println("Hello");
                return null;
            });

            scope.join();           // Join both forks
            scope.throwIfFailed();  // ... and propagate errors
        }
    }
}
