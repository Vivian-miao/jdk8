package com.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>FilterApple</h1>
 *
 * @author zhh 2019-04-23
 */
public class FilterApple {

    private static List<Apple> findApple(List<Apple> apples) {
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * <h2>过滤器</h2>
     */
    @FunctionalInterface
    private interface AppleFilter {
        /**
         * 过滤器
         *
         * @param apple
         * @return
         */
        boolean filter(Apple apple);
    }

    private static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> apples = Arrays.asList(new Apple("green", 80), new Apple("blue", 90), new Apple("green", 60));
        List<Apple> list = findApple(apples);
        assert list.size() == 2;
        System.out.println(list);

        List<Apple> green = findApple(list, apple -> apple.getColor().equals("green"));

        System.out.println(green);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Thread.currentThread().join();
    }
}
