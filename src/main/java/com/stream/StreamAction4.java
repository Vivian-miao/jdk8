package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-21
 */
public class StreamAction4 {
    public static void main(String[] args) {
        wordLength();
        System.out.println("----------");
        distinctWord();
        System.out.println("----------");
        spliceWord();
    }

    /**
     * 单词长度
     */
    private static void wordLength() {
        Stream<String> stream = Stream.of("hello", "world", "jak8", "lambda");
        stream.mapToInt(String::length).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
    }

    /**
     * 单词去重
     */
    private static void distinctWord() {
        Stream<String> strings = Stream.of("hello java", "hello world", "hello java", "java", "hello", "world");
        strings.map(str -> str.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }

    /**
     * list1循环拼接list2
     */
    private static void spliceWord() {
        List<String> greet = Arrays.asList("hi", "hello", "hey");
        List<String> names = Arrays.asList("mike", "john", "jean");
        Optional.of(greet.stream()
                .flatMap(item -> names.stream().map(name -> item + " " + name))
                .collect(Collectors.toList()))
                .ifPresent(System.out::println);
    }
}
