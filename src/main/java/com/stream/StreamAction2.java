package com.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-21
 */
public class StreamAction2 {
    public static void main(String[] args) {
        Stream<String> stream = getStringStream();
        Optional.of(stream.map(String::toUpperCase).collect(Collectors.toList())).ifPresent(System.out::println);
        System.out.println("----------");
        Stream<Integer> integers = getIntegerStream();
        Optional.of(integers.map(integer -> integer * integer).collect(Collectors.toList())).ifPresent(System.out::println);
        System.out.println("----------");
        Stream<List<Integer>> list = Stream.of(Arrays.asList(1, 4, 9, 16, 25));
        Optional.of(list.flatMap(Collection::stream).map(integer -> integer * integer).collect(Collectors.toList())).ifPresent(System.out::println);
        System.out.println("----------");
        generate();
        System.out.println("----------");
        empty();
        System.out.println("----------");
        createStreamFromIterate();
        System.out.println("----------");
        computeSum();
        System.out.println("----------");
        computeMin();
        System.out.println("----------");
        getSummaryStatistics();
        System.out.println("----------");
        createStreamFromIterate2();
    }

    private static Stream<String> getStringStream() {
        return Stream.of("hello", "world", "jdk8", "lambda", "apple");
    }

    private static Stream<Integer> getIntegerStream() {
        return Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});
    }

    private static void generate() {
        Stream.generate(UUID.randomUUID()::toString).findFirst().ifPresent(System.out::println);
    }

    private static void empty() {
        Stream.empty().findFirst().ifPresent(System.out::println);
    }

    private static void createStreamFromIterate() {
        Stream.iterate(1, num -> num + 2).limit(6).forEach(System.out::println);
    }

    private static void createStreamFromIterate2() {
        Stream.iterate(0, integer -> (integer + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }

    /**
     * 找出流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，接着再取流中的前两个元素，最后求出流中元素的和。
     */
    private static void computeSum() {
        Optional.of(Stream.iterate(1, num -> num + 2).limit(6)
                .filter(integer -> integer > 2)
                .mapToInt(integer -> integer * 2)
                .skip(2).limit(2).sum())
                .ifPresent(System.out::println);
    }

    /**
     * 求最值
     */
    private static void computeMin() {
        Stream.iterate(1, num -> num + 2).limit(6)
                .filter(integer -> integer > 2)
                .mapToInt(integer -> integer * 2)
                .skip(2).limit(2).max()
                .ifPresent(System.out::println);
    }

    private static void getSummaryStatistics() {
        Optional.ofNullable(Stream.iterate(1, num -> num + 2).limit(6)
                .filter(integer -> integer > 2)
                .mapToInt(integer -> integer * 2)
                .skip(2).limit(2).summaryStatistics())
                .ifPresent(System.out::println);
    }

}
