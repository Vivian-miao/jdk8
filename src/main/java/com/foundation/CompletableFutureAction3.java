package com.foundation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class CompletableFutureAction3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2, factory -> {
            Thread thread = new Thread(factory);
            thread.setDaemon(false);
            return thread;
        });

        /*CompletableFuture.supplyAsync(CompletableFutureAction1::get, service)
                .thenApply(CompletableFutureAction3::multiply)
                .whenComplete((key, throwable) -> Optional.of(key).ifPresent(System.out::println));*/

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> collect = integers.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> query(integer), service))
                .map(future -> future.thenApply(CompletableFutureAction3::multiply))
                .parallel().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(collect);

    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

    private static double query(int i) {
        return CompletableFutureAction1.get();
    }
}
