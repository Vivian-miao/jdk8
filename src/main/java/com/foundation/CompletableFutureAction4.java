package com.foundation;

import java.util.concurrent.CompletableFuture;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class CompletableFutureAction4 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(integer -> Integer.sum(integer, 10))
                .whenComplete((value, throwable) -> System.out.println(value));

        CompletableFuture.supplyAsync(() -> 1)
                .handle((value, throwable) -> Integer.sum(value, 10))
                .whenComplete((value, throwable) -> System.out.println(value))
                .thenRun(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(integer -> Integer.sum(integer, 10))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> 10 * integer))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), Double::sum)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (num1, num2) -> {
                    System.out.println(num1);
                    System.out.println(num2);
                    System.out.println(num1 + num2);
                });

        Thread.sleep(1000L);
    }
}
