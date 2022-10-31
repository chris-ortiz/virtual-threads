package de.adesso.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class B_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        var random = new Random();
        var countDownLatch = new CountDownLatch(100_000);

        for (int i = 0; i < 500_000; i++) {
            Thread.ofVirtual().start(() -> {
                System.out.println(random.nextInt());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
    }
}
