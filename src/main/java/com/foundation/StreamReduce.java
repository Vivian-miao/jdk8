package com.foundation;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <h1>StreamReduce</h1>
 *
 * @author zhh 2019-04-24
 */
public class StreamReduce {
    public static void main(String[] args) {
        Stream<Integer> stream = getIntegerStream();
        // 元素求和：使用Integer.sum函数求和
        Integer result = stream.reduce(0, Integer::sum);
        System.out.println(result);

        // 求和
        stream = getIntegerStream();
        stream.reduce(Integer::sum).ifPresent(System.out::println);

        // 取最值
        stream = getIntegerStream();
        stream.reduce((i,j)-> i>j?i:j).ifPresent(System.out::println);

        stream = getIntegerStream();
        stream.reduce(Integer::min).ifPresent(System.out::println);

        stream = getIntegerStream();
        stream.reduce(Integer::max).ifPresent(System.out::println);

        stream = getIntegerStream();
        Integer reduce = stream.filter(integer -> integer % 2 == 0).reduce(1, (i, j) -> i * j);
        System.out.println(reduce);
        Optional.of(reduce).ifPresent(System.out::println);
    }

    @NotNull
    private static Stream<Integer> getIntegerStream() {
        return Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});
    }
}
