package com.foundation;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.foundation.CollectorsAction.dishes;

/**
 * <h1>Collector 方法</h1>
 *
 * @author zhh 2019-05-14
 */
public class CollectorsAction4 {
    public static void main(String[] args) {
        System.out.println("----------");
        summingDouble();
        System.out.println("----------");
        summingLong();
        System.out.println("----------");
        summingInt();
        System.out.println("----------");
        toCollection();
        System.out.println("----------");
        toConcurrentMap();
        System.out.println("----------");
        toConcurrentMapBinaryOperator();
        System.out.println("----------");
        toConcurrentMapBinaryOperatorAndSupplier();
        System.out.println("----------");
        toList();
        System.out.println("----------");
        toSet();
        System.out.println("----------");
        toMap();
        System.out.println("----------");
        toMapBinaryOperator();
        System.out.println("----------");
        toMapBinaryOperatorAndSupplier();
        System.out.println("----------");
        listToMap();
        System.out.println("----------");
        lowCaloricDishesName();
    }

    /**
     * 统计
     */
    private static void summingDouble() {
        Optional.of(dishes.stream().collect(Collectors.summingDouble(Dish::getCalories))).ifPresent(System.out::println);
        Optional.of(dishes.stream().mapToDouble(Dish::getCalories).sum()).ifPresent(System.out::println);
        Optional.of(dishes.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum()).ifPresent(System.out::println);
    }

    private static void summingLong() {
        Optional.of(dishes.stream().mapToLong(Dish::getCalories).sum()).ifPresent(System.out::println);
    }

    private static void summingInt() {
        Optional.of(dishes.stream().collect(Collectors.summingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

    /**
     * 返回一个按顺序Collector将输入元素累积为新的元素Collection。
     */
    private static void toCollection() {
        Optional.of(dishes.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toCollection(LinkedHashSet::new)))
                .ifPresent(System.out::println);
    }

    /**
     * 返回将Collector元素累积到其中 ConcurrentMap的并发函数，其键和值是将提供的映射函数应用于输入元素的结果。
     */
    private static void toConcurrentMap() {
        Optional.of(dishes.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
                .ifPresent(map -> {
                    System.out.println(map);
                    System.out.println(map.getClass());
                });
    }

    private static void toConcurrentMapBinaryOperator() {
        Optional.of(dishes.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum)))
                .ifPresent(map -> {
                    System.out.println(map);
                    System.out.println(map.getClass());
                });
    }

    private static void toConcurrentMapBinaryOperatorAndSupplier() {
        Optional.of(dishes.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum, ConcurrentSkipListMap::new)))
                .ifPresent(map -> {
                    System.out.println(map);
                    System.out.println(map.getClass());
                });
    }

    /**
     * 返回一个Collector将输入元素累积为new的元素List。
     */
    private static void toList() {
        Optional.of(dishes.stream().filter(Dish::isVegetable).collect(Collectors.toList()))
                .ifPresent(result -> {
                    System.out.println(result);
                    System.out.println(result.getClass());
                });
    }

    /**
     * 返回一个Collector将输入元素累积为new的元素Set。
     */
    private static void toSet() {
        Optional.of(dishes.stream().filter(Dish::isVegetable).collect(Collectors.toSet()))
                .ifPresent(result -> {
                    System.out.println(result);
                    System.out.println(result.getClass());
                });
    }

    /**
     * 返回将Collector元素累积到 Map其键中的值，其值是将提供的映射函数应用于输入元素的结果。
     */
    private static void toMap() {
        // 转换成线程安全的map
        Optional.of(dishes.stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(Dish::getName, Dish::getCalories), Collections::synchronizedMap)))
                .ifPresent(System.out::println);

        Optional.of(dishes.stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getCalories)))
                .ifPresent(map -> {
                    System.out.println(map);
                    System.out.println(map.getClass());
                });
    }

    private static void toMapBinaryOperator() {
        Optional.of(dishes.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1, Integer::sum)))
                .ifPresent(map -> {
                    System.out.println(map);
                    System.out.println(map.getClass());
                });
    }

    private static void toMapBinaryOperatorAndSupplier() {
        Optional.of(dishes.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1, Integer::sum, Hashtable::new)))
                .ifPresent(map -> {
                    System.out.println(map);
                    System.out.println(map.getClass());
                });
    }

    private static void listToMap() {
        /*针对重复key的，覆盖之前的value；value为空，直接存放，不调用map.merge */
        HashMap<String, Integer> dishesMap = dishes.stream().collect(Collector.of(HashMap::new, (map, dish) -> map.put(dish.getName(), dish.getCalories()), (key, value) -> value, Collector.Characteristics.IDENTITY_FINISH));
        Map<String, Integer> dishMap = dishes.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories));
        for (Map.Entry<String, Integer> entry : dishMap.entrySet()) {
            System.out.println(entry);
            if ("chicken".equals(entry.getKey())) {
                System.out.println(entry.getValue());
            }
        }
        for (String key : dishesMap.keySet()) {
            if ("pizza".equals(key)) {
                System.out.println(dishesMap.get(key));
            }
        }
    }

    private static void lowCaloricDishesName() {
        Optional.of(dishes.parallelStream().filter(dish -> dish.getCalories() > 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList()))
                .ifPresent(System.out::println);
    }

}
