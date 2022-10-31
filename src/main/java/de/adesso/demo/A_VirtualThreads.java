package de.adesso.demo;

import java.util.concurrent.ThreadFactory;

public class A_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual()
                .start(() -> System.out.println(Thread.currentThread())).join();

        Thread.ofPlatform()
                .start(() -> System.out.println(Thread.currentThread())).join();

        Thread.ofVirtual().unstarted(() -> {}).start();

        ThreadFactory factory = Thread.ofVirtual().factory();
        factory.newThread(() -> {}).start();
    }
}