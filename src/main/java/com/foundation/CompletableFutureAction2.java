package com.foundation;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class CompletableFutureAction2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ExecutorService service = Executors.newFixedThreadPool(2, factory -> {
            Thread thread = new Thread(factory);
            thread.setDaemon(false);
            return thread;
        });

        CompletableFuture.supplyAsync(CompletableFutureAction1::get, service).whenComplete((key, throwable) -> {
            Optional.of(key).ifPresent(System.out::println);
            Optional.of(throwable).ifPresent(Throwable::printStackTrace);
            atomicBoolean.set(true);
        });
        System.out.println("no block");
        // Thread.currentThread().join();

        while (!atomicBoolean.get()) {
            Thread.sleep(1);
        }
    }
}
