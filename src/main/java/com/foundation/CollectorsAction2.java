package com.foundation;

import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.foundation.CollectorsAction.dishes;

/**
 * <h1>Collector 方法</h1>
 *
 * @author zhh 2019-05-14
 */
public class CollectorsAction2 {
    public static void main(String[] args) {
        System.out.println("----------");
        groupingByConcurrent();
        System.out.println("----------");
        groupingByConcurrentWithFunctionAndCollector();
        System.out.println("----------");
        groupingByConcurrentWithFunctionAndSupplierAndCollector();
        System.out.println("----------");
        joining();
        System.out.println("----------");
        joiningWithDelimiter();
        System.out.println("----------");
        joiningWithDelimiterAndPrefixAndSuffix();
        System.out.println("----------");
        mapping();
        System.out.println("----------");
        maxBy();
        System.out.println("----------");
        minBy();
    }

    /**
     * 根据类型分组
     */
    private static void groupingByConcurrent() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType)))
                .ifPresent(System.out::println);
        System.out.println("--------------------");
        Optional.ofNullable(Maps.uniqueIndex(dishes, Dish::getName)).ifPresent(System.out::println);
    }

    /**
     * 根据类型分组，计算平均值
     */
    private static void groupingByConcurrentWithFunctionAndCollector() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    /**
     * 根据类型分组，计算平均值
     */
    private static void groupingByConcurrentWithFunctionAndSupplierAndCollector() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    /**
     * 对Stream的字符串拼接
     */
    private static void joining() {
        Optional.ofNullable(dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.joining()))
                .ifPresent(System.out::println);
    }

    private static void joiningWithDelimiter() {
        Optional.ofNullable(dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",")))
                .ifPresent(System.out::println);
    }

    private static void joiningWithDelimiterAndPrefixAndSuffix() {
        Optional.ofNullable(dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",", "Names[", "]")))
                .ifPresent(System.out::println);
    }

    private static void mapping() {
        Optional.of(dishes.stream()
                .collect(Collectors.mapping(Dish::getName, Collectors.joining(","))))
                .ifPresent(System.out::println);
    }

    /**
     * 按给定比较器选出最大元素
     */
    private static void maxBy() {
        dishes.stream().max(Comparator.comparingInt(Dish::getCalories)).ifPresent(System.out::println);
    }

    /**
     * 按给定比较器选出最小元素
     */
    private static void minBy() {
        dishes.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);
    }
}
