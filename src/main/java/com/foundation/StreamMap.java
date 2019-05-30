package com.foundation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;

/**
 * <h1>StreamMap</h1>
 *
 * @author zhh 2019-04-24
 */
public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = asList(1, 2, 3, 4, 5, 6, 6, 7, 4);
        // 映射
        // 对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。流会将每一个元素输送给map函数，并执行map中的Lambda表达式，最后将执行结果存入一个新的流中。
        List<Integer> collect = list.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(collect);
        // 获取dish的名字(实则是将Dish类型转换成String类型)
        // listDish().stream().map(Dish::getName).forEach(System.out::println);
        List<String> strings = listDish().stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(strings);

        // 将小流合并成一个大流：用flatMap替换刚才的map
        String[] words = {"hello", "world"};
        // Stream<String[]>, {h,e,l,l,o},{w,o,r,l,d}
        stream(words).map(w -> w.split(""))
                // h,e,l,l,o,w,o,r,l,d
                .flatMap(Arrays::stream)
                // h,e,l,o,w,o,r,d
                .distinct().forEach(System.out::println);

        Stream<String[]> stream = stream(words).map(w -> w.split(""));
        System.out.println(stream);
        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        System.out.println(stringStream);
        stringStream.distinct().forEach(System.out::println);
    }

    private static List<Dish> listDish() {
        return asList(
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
    }
}
