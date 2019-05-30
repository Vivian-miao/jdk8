package com.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-21
 */
public class StreamAction {
    public static void main(String[] args) {
        Stream<String> stream = getStringStream();
        String[] array = stream.toArray(String[]::new);
        Arrays.asList(array).forEach(System.out::println);

        IntStream.of(5, 6, 7).forEach(System.out::println);
        System.out.println("----------");
        IntStream.range(2, 7).forEach(System.out::println);
        System.out.println("----------");
        IntStream.rangeClosed(2, 7).forEach(System.out::println);
        System.out.println("----------");
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        integers.stream()
                .map(integer -> integer * 2)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
        System.out.println("----------");

        List<String> collect = getStringStream().collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("----------");
        stream = getStringStream();
        LinkedList<Object> list = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        list.forEach(System.out::println);
        System.out.println("----------");
        stream = getStringStream();
        ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(arrayList);
        System.out.println("----------");
        stream = getStringStream();
        TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);

        System.out.println("----------");
        stream = getStringStream();
        String collectJoin = stream.collect(Collectors.joining(","));
        System.out.println(collectJoin);
        System.out.println("----------");
        stream = getStringStream();
        stream.map(item -> {
            String result = item.substring(0, 1).toUpperCase() + item.substring(1);
            System.out.println("item");
            return result;
        }).forEach(System.out::println);

    }

    private static Stream<String> getStringStream() {
        return Stream.of("hello", "world", "jdk8", "lambda", "apple");
    }

}
