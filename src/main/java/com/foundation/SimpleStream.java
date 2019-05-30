package com.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>stream 的简单测试</h1>
 *
 * @author zhh 2019-04-24
 */
public class SimpleStream {
    public static void main(String[] args) {
        List<Dish> dishes = Arrays.asList(
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

        dishes.forEach(System.out::println);

        System.out.println("---------------");

        List<String> collect = dishes.stream().filter(dish -> {
            System.out.println("filtering->" + dish.getName());
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("map->" + dish.getName());
            return dish.getName();
        }).limit(3).collect(Collectors.toList());

        System.out.println("---------------");

        System.out.println(collect);

        System.out.println("---------------");

        Stream<Dish> dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 400, Dish.Type.FISH));
        dishStream.forEach(System.out::println);

        /*List<String> strings = getDishNameByList(dishes);
        System.out.println(strings);

        List<String> dishNameByStream = getDishNameByStream(dishes);
        System.out.println(dishNameByStream);*/
    }

    /**
     * <h2>传统方法</h2>
     * <h2>过滤卡路里<400的食物，并获取名字</h2>
     *
     * @param dishes
     * @return
     */
    private static List<String> getDishNameByList(List<Dish> dishes) {
        List<Dish> lowCalories = new ArrayList<>();
        // 获取卡路里低于400的食物
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 排序
        lowCalories.sort(Comparator.comparing(Dish::getCalories));
        // 获取排序后的list的名称，添加到dishNameList里
        List<String> dishNameList = new ArrayList<>();
        for (Dish dish : lowCalories) {
            dishNameList.add(dish.getName());
        }
        return dishNameList;
    }

    /**
     * <h2>用stream方法过滤卡路里<400的食物，并获取名字</h2>
     *
     * @param dishes
     * @return
     */
    private static List<String> getDishNameByStream(List<Dish> dishes) {
        /*return dishes.stream().filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());*/
        // 并行处理
        return dishes.parallelStream().filter(dish -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return dish.getCalories() < 400;
        }).sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
