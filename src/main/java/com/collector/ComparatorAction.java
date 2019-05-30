package com.collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.of;

/**
 * <h1>比较器</h1>
 *
 * @author zhh 2019-05-22
 */
public class ComparatorAction {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hello", "lambda", "java", "world", "function");
        Collections.sort(strings);
        System.out.println(strings);
        System.out.println("----------");
        stringCompareTo(strings);
        System.out.println("----------");
        sortByLength(strings);
        System.out.println(strings);
        System.out.println("----------");
        strings.sort(Comparator.comparingInt(string -> string.length()));
        System.out.println("----------");
        // 先按照length升序，然后根据字母的ASCII码排序
        strings.sort(Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(strings);
        System.out.println("----------");
        strings.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toUpperCase)));
        System.out.println(strings);
        System.out.println("----------");
        strings.sort(Comparator.comparingInt(String::length).thenComparing(String::toLowerCase,Comparator.reverseOrder()));
        System.out.println(strings);
        System.out.println("----------");
        strings.sort(Comparator.comparingInt(String::length).reversed().thenComparing(String::toLowerCase,Comparator.reverseOrder()));
        System.out.println(strings);
        System.out.println("----------");
        // 最后的 thenComparing(Comparator.reverseOrder()) 不比较
        strings.sort(Comparator.comparingInt(String::length).reversed().thenComparing(String::toLowerCase,Comparator.reverseOrder()).thenComparing(Comparator.reverseOrder()));
        System.out.println(strings);
        System.out.println("----------");
    }

    /**
     * 按字母顺序进行升序排序
     */
    private static void stringCompareTo(List<String> strings) {
        of(strings.stream().sorted(String::compareTo).collect(Collectors.toList())).ifPresent(System.out::println);
    }

    /**
     * 按字母长度进行排序
     */
    private static void sortByLength(List<String> strings) {
        strings.sort(Comparator.comparingInt(String::length).reversed());
    }
}
