package de.adesso.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class B_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        var random = new Random();
        var threadCount = 500_000;
        var countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread.ofVirtual().start(() -> {
                System.out.println(random.nextInt());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
    }
}
