package de.adesso.demo;

import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class D_BlockingTasks {
    static int TASK_COUNT = 5000;

    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        Instant start = Instant.now();

        blockingTasks(Executors.newVirtualThreadPerTaskExecutor());
//        blockingTasks(Executors.newCachedThreadPool());

        Instant finish = Instant.now();

        System.out.printf("Duration: %s%n", Duration.between(start, finish).toString());
    }


    static void blockingTasks(ExecutorService executorService) throws InterruptedException {
        Runnable blockingTask = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var tasks = IntStream.range(0, TASK_COUNT)
                .mapToObj(i -> Executors.callable(blockingTask)).toList();
        executorService.invokeAll(tasks);

        executorService.shutdownNow();
    }
}
