package com.collector;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-27
 */
public class ConsumerExample {
    public void test(Consumer<Integer> consumer) {
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerExample example = new ConsumerExample();
        Consumer<Integer> consumer = System.out::println;
        IntConsumer intConsumer = System.out::println;

        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof IntConsumer);

        // 面向对象方式
        example.test(consumer);
        // 函数式方式
        example.test(consumer::accept);
        // 函数式方式
        example.test(intConsumer::accept);

    }
}
