package de.adesso.demo;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class C_VirtualThreads {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            var random = new Random();

//            var future = executorService.submit(() -> random.nextInt());
//
//            System.out.println(future.get());


            var fromCompletableFuture = CompletableFuture
                    .supplyAsync(random::nextInt, executorService).get();

            System.out.println(fromCompletableFuture);

        }

    }
}
