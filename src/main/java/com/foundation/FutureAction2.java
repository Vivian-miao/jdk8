package com.foundation;

import java.util.concurrent.*;

/**
 * <h1>多线程</h1>
 *
 * @author zhh 2019-05-15
 */
public class FutureAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        // System.out.println(future.get(10, TimeUnit.MICROSECONDS));
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        System.out.println(future.get());
        executor.shutdown();
    }
}
