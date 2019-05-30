package com.foundation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

/**
 * <h1>Apple 测试类</h1>
 *
 * @author zhh 2019-05-17
 */
public class AppleExample {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("green", 80),
                new Apple("blue", 90),
                new Apple("green", 60),
                new Apple("yellow", 80),
                new Apple("blue", 90),
                new Apple("green", 60));
        getAppleByColor("green", apples);
        System.out.println("----------");
        getAppleByWeight(60, apples);
        System.out.println("----------");
        getAppleByWeight(80, apples, (weight, appleList) -> appleList.stream().filter(apple -> apple.getWeight() <= weight).collect(toList()));
        System.out.println("----------");
        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println(apples);
    }

    private static void getAppleByColor(String color, List<Apple> apples) {
        of(apples.stream().filter(apple -> color.equals(apple.getColor())).collect(toList()))
                .ifPresent(System.out::println);
    }

    private static void getAppleByWeight(long weight, List<Apple> apples) {
        BiFunction<Long, List<Apple>, List<Apple>> biFunction = (appleOfWeight, appleList) -> appleList.stream().filter(apple -> apple.getWeight() > appleOfWeight).collect(toList());
        of(biFunction.apply(weight, apples)).ifPresent(System.out::println);
    }

    private static void getAppleByWeight(long weight, List<Apple> apples, BiFunction<Long, List<Apple>, List<Apple>> biFunction) {
        of(biFunction.apply(weight, apples)).ifPresent(System.out::println);
    }
}
