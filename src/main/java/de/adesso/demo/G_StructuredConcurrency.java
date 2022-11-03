package de.adesso.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class G_StructuredConcurrency {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<UUID> world = scope.fork(() -> {
                Thread.sleep(100);
                System.out.println("World");
                return null;
            });

            Future<UUID> hello = scope.fork(() -> {
                System.out.println("Hello");
                return null;
            });

            scope.join();           // Join both forks
            scope.throwIfFailed();  // ... and propagate errors
        }
    }
}
