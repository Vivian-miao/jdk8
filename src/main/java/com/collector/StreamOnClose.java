package com.collector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-24
 */
public class StreamOnClose {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "jdk", "lambda");
        NullPointerException exception = new NullPointerException("exception");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaa");
                // throw new NullPointerException("aaa exception");
                throw exception;
            }).onClose(() -> {
                System.out.println("bbb");
                // throw new NullPointerException("bbb exception");
                throw exception;
            }).forEach(System.out::println);
        }
    }
}
