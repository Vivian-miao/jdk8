package com.foundation;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-20
 */
public class BinaryOperatorExample {
    public static void main(String[] args) {
        binaryOperatorApply(1, 2, Integer::sum);
        System.out.println("----------");
        binaryOperatorApply(1, 2, (num1, num2) -> num1 - num2);
        System.out.println("----------");
        getMin("hello123", "world", Comparator.comparingInt(String::length));
        System.out.println("----------");
        getMax("hello", "world", Comparator.comparingInt(String::length));
    }

    private static void binaryOperatorApply(int a, int b, BinaryOperator<Integer> binaryOperator) {
        Optional.of(binaryOperator.apply(a, b)).ifPresent(System.out::println);
    }

    private static void getMin(String a, String b, Comparator<String> comparator) {
        Optional.of(BinaryOperator.minBy(comparator).apply(a, b)).ifPresent(System.out::println);
    }

    private static void getMax(String a, String b, Comparator<String> comparator) {
        Optional.of(BinaryOperator.maxBy(comparator).apply(a, b)).ifPresent(System.out::println);
    }
}
