package com.foundation;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h1>多线程</h1>
 *
 * @author zhh 2019-05-15
 */
public class FutureAction {

    public static void main(String[] args) {
        /*Future<String> future = invoke(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());

        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());*/

        String block = block(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        System.out.println(block);
    }

    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        new Thread(() -> {
            T value = callable.action();
            reference.set(value);
            finished.set(true);
        }).start();

        return new Future<>(){

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
    }

    private interface Future<T> {
        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }

}
