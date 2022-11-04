package de.adesso.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;

public class I_StructuredConcurrency {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(() -> {
                Thread.sleep(100);
                System.out.println("World");
                return "World";
            });

            scope.fork(() -> "Hello");

            scope.join();
            System.out.println(scope.result());
        }
    }
}
