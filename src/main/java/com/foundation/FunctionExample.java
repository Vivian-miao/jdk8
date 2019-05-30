package com.foundation;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-16
 */
public class FunctionExample {

    public static void main(String[] args) {
        // 7
        System.out.println(compute(1, value -> 6 + value));
        // 8:2*2*2
        Optional.of(compute(2, value -> value * 2, value -> value * value)).ifPresent(System.out::println);
        // 16:2*2=4,4*4
        Optional.of(compute2(2, value -> value * 2, value -> value * value)).ifPresent(System.out::println);

        // 3
        Optional.of(compute3(1,2, Integer::sum)).ifPresent(System.out::println);

        // 25:2+3=5,5*5
        Optional.of(compute(2, 3, Integer::sum, value -> value * value)).ifPresent(System.out::println);
    }

    private static int compute(int val, Function<Integer, Integer> function) {
        return function.apply(val);
    }

    private static int compute(int val, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(val);
    }

    private static int compute2(int val, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(val);
    }

    private static int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    private static int compute(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }
}
