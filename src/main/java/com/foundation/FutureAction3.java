package com.foundation;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class FutureAction3 {
    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "finish";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println(cause);
                cause.printStackTrace();
            }
        });

        System.out.println(future.get());
        System.out.println("-----");
    }

    private static <T> FutureAction3.Future<T> invoke(FutureAction3.Callable<T> callable) {
        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new Future<>() {
            private Completable<T> completable;

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };
        new Thread(() -> {
            try {
                T value = callable.action();
                reference.set(value);
                finished.set(true);
                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            } catch (Throwable cause) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(cause);
                }
            }

        }).start();

        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T> {
        void complete(T t);

        void exception(Throwable cause);
    }

}
