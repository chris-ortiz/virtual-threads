package de.adesso.demo;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class E_HttpTasks {
    public static void main(String[] args) throws InterruptedException {
        var start = Instant.now();
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            var callables = IntStream.range(0, 100).mapToObj(i ->
                    (Callable<String>) () -> {
                        var url = new URL("https://postman-echo.com/get");
                        try (var in = url.openStream()) {
                            return new String(in.readAllBytes());
                        }
                    }).toList();

            executorService.invokeAll(callables);

            var end = Instant.now();

            System.out.println("Duration = " + Duration.between(start, end));
        }

    }

}
