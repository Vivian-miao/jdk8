package com.foundation;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <h1>CompletableFuture</h1>
 *
 * @author zhh 2019-05-15
 */
public class CompletableFutureAction1 {
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            future.complete(value);
        }).start();
        System.out.println("no block");

        future.whenComplete((key, throwable) -> {
            Optional.of(key).ifPresent(System.out::println);
            Optional.of(throwable).ifPresent(Throwable::printStackTrace);
        });
    }

    static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(RANDOM.nextDouble());
        return RANDOM.nextDouble();
    }
}
