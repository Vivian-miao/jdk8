package com.foundation;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class CompletableFutureAction5 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 2;
        }), () -> System.out.println("done"));

        CompletableFuture.supplyAsync(() -> {
            System.out.println("future 1");
            return CompletableFutureAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("future 2");
            return CompletableFutureAction1.get();
        }), value -> value * 10).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("future 1");
            return CompletableFutureAction1.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("future 2");
            return CompletableFutureAction1.get();
        }), System.out::println);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("future 1");
            return CompletableFutureAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("future 2");
            return CompletableFutureAction1.get();
        }), () -> System.out.println("done"));

        CompletableFuture.allOf(Stream.of(1, 2, 3, 4)
                .map(integer -> CompletableFuture.supplyAsync(CompletableFutureAction1::get)).toArray(CompletableFuture[]::new))
                .thenRun(() -> System.out.println("done"));

        Thread.currentThread().join();
    }
}
