package com.foundation;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.foundation.CollectorsAction.dishes;

/**
 * <h1>Collector 方法</h1>
 *
 * @author zhh 2019-05-14
 */
public class CollectorsAction3 {
    public static void main(String[] args) {
        System.out.println("----------");
        partitioningByWithPredicate();
        System.out.println("----------");
        partitioningByWithPredicateAndCollector();
        System.out.println("----------");
        reducingBinaryOperator();
        System.out.println("----------");
        reducingBinaryOperatorAndIdentity();
        System.out.println("----------");
        reducingBinaryOperatorAndIdentityAndFunction();
        System.out.println("----------");
        summarizingDouble();
        System.out.println("----------");
        summarizingLong();
        System.out.println("----------");
        summarizingInt();
        System.out.println("----------");
    }

    /**
     * 根据条件分组
     */
    private static void partitioningByWithPredicate() {
        Optional.of(dishes.stream().collect(Collectors.partitioningBy(Dish::isVegetable)))
                .ifPresent(System.out::println);
    }

    private static void partitioningByWithPredicateAndCollector() {
        Optional.of(dishes.stream().collect(Collectors.partitioningBy(Dish::isVegetable,Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    /**
     * 聚合
     */
    private static void reducingBinaryOperator() {
        dishes.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))))
                .ifPresent(System.out::println);

        dishes.stream().reduce(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 累加
     */
    private static void reducingBinaryOperatorAndIdentity() {
        Integer reduce = dishes.stream()
                .map(Dish::getCalories).reduce(0, (dishes1, dishes2) -> dishes1 + dishes2);
        System.out.println(reduce);

        Integer collect = dishes.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, (dishes1, dishes2) -> dishes1 + dishes2));
        System.out.println(collect);
    }

    private static void reducingBinaryOperatorAndIdentityAndFunction() {
        Optional.of(dishes.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (dishes1, dishes2) -> dishes1 + dishes2)))
                .ifPresent(System.out::println);
    }

    /**
     * 返回一个Collector将生成double映射函数应用于每个输入元素的函数，并返回结果值的汇总统计信息
     */
    private static void summarizingDouble() {
        Optional.of(dishes.stream().collect(Collectors.summarizingDouble(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void summarizingLong() {
        Optional.of(dishes.stream().collect(Collectors.summarizingLong(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void summarizingInt() {
        Optional.of(dishes.stream().collect(Collectors.summarizingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

}
