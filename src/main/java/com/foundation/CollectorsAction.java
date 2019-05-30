package com.foundation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>Collector 方法</h1>
 *
 * @author zhh 2019-05-13
 */
public class CollectorsAction {
    public final static List<Dish> dishes = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 400, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        averagingDouble();
        System.out.println("----------");
        averagingInt();
        System.out.println("----------");
        averagingLong();
        System.out.println("----------");
        summingInt();
        System.out.println("----------");
        summarizingInt();
        System.out.println("----------");
        collectingAndThen();
        System.out.println("----------");
        counting();
        System.out.println("----------");
        groupingByFunction();
        System.out.println("----------");
        groupingByFunctionAndCollector();
        System.out.println("----------");
        groupingByFunctionAndSupplierAndCollector();
    }

    /**
     * 计算流中属性的平均值
     */
    private static void averagingDouble() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void averagingInt() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void averagingLong() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 收集流中Integer属性的统计值
     */
    private static void summingInt() {
        // Optional.ofNullable(dishes.stream().collect(Collectors.summingInt(Dish::getCalories))).ifPresent(System.out::println);
        Optional.of(dishes.stream()
                .mapToInt(Dish::getCalories).sum())
                .ifPresent(System.out::println);
    }

    /**
     * 收集流中Integer属性的统计值
     */
    private static void summarizingInt() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 收集之后继续做一些处理
     */
    private static void collectingAndThen() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "average calories is：" + a)))
                .ifPresent(System.out::println);

        Optional.ofNullable(dishes.stream()
                .filter(dish -> Dish.Type.MEAT.equals(dish.getType()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)))
                .ifPresent(System.out::println);
    }

    /**
     * 计算流中的元素个数
     */
    private static void counting() {
        /*Optional.of(dishes.stream()
                .collect(Collectors.counting()))
                .ifPresent(System.out::println);*/
        Optional.of((long) dishes.size()).ifPresent(System.out::println);
    }

    /**
     * 根据流中一个属性的值做分组，并以该属性为Map的一个Key
     */
    private static void groupingByFunction() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }

    /**
     * 根据流中一个属性的值做分组，并计算流中的元素个数/平均值
     */
    private static void groupingByFunctionAndCollector() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);

        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    /**
     * 根据流中一个属性的值做分组，转成treeMap，计算流中的元素个数
     */
    private static void groupingByFunctionAndSupplierAndCollector() {
        Optional.ofNullable(dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,TreeMap::new, Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }
}
