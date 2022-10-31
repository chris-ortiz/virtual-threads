package de.adesso.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class F_LimitHttpTasks {

    /**
     * Beispiel: Bottleneck externer service, max 8 permits
     */
    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        Instant start = Instant.now();

        var semaphore = new Semaphore(8);
        var executorService = Executors.newVirtualThreadPerTaskExecutor();
        var request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();

        var httpClient = HttpClient.newHttpClient();
        Callable<String> httpTask = () -> {
            try {
                semaphore.acquire();
                return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } finally {
                semaphore.release();
            }
        };

        var tasks = IntStream.range(0, 50).mapToObj(i -> httpTask).toList();

        executorService.invokeAll(tasks);

        Instant finish = Instant.now();

        System.out.printf("Duration: %s%n", Duration.between(start, finish).toString());
    }


}