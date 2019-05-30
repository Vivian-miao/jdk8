package com.collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-28
 */
public class StreamExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "java", "jdk");
        list.forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        List<Object> objects = Collections.emptyList();
        Objects.requireNonNull(objects);
        System.out.println(objects);

        Stream<String> stream = list.stream();
        System.out.println("11111");
        Stream<String> stream1 = stream.map(str -> str + "_abc");
        System.out.println("22222");
        stream1.forEach(System.out::println);
    }
}
