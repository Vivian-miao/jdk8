package com.foundation;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h1>SteamFind</h1>
 *
 * @author zhh 2019-04-24
 */
public class SteamFind {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});

        // 获取任一元素findAny
        Optional<Integer> any = stream.filter(integer -> integer % 2 == 0).findAny();
        System.out.println(any.orElse(-1));

        int result = find(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1}, -1, integer -> integer > 5);
        System.out.println(result);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});
        // 获取第一个元素findFirst
        Optional<Integer> first = stream.filter(integer -> integer % 2 == 0).findFirst();
        first.ifPresent(System.out::println);

        System.out.println(first.filter(integer -> integer == 2).get());
    }

    private static int find(Integer[] integers, int value, Predicate<Integer> predicate) {
        for (Integer integer : integers) {
            if (predicate.test(integer)) {
                return integer;
            }
        }
        return value;
    }
}
