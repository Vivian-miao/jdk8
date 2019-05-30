package com.foundation;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-13
 */
public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("green", 80),
                new Apple("blue", 90),
                new Apple("green", 60),
                new Apple("yellow", 80),
                new Apple("blue", 90),
                new Apple("green", 60));
        List<Apple> colorList = apples.stream().filter(apple -> "green".equals(apple.getColor())).collect(Collectors.toList());
        System.out.println(colorList);

        Optional.of(getListByNormal(apples)).ifPresent(System.out::println);

        System.out.println("----------");

        Optional.of(groupByFunction(apples)).ifPresent(System.out::println);

        System.out.println("----------");

        Optional.of(groupByCollector(apples)).ifPresent(System.out::println);



    }

    private static Map<String, List<Apple>> getListByNormal(List<Apple> apples) {
        HashMap<String, List<Apple>> map = Maps.newHashMap();
        for (Apple apple : apples) {
            /*List<Apple> list = map.get(apple.getColor());
            /*if (list == null) {
                list = Lists.newArrayList();
                map.put(apple.getColor(), list);
            }*/

            List<Apple> list = map.computeIfAbsent(apple.getColor(), k -> Lists.newArrayList());
            list.add(apple);
        }
        return map;
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        HashMap<String, List<Apple>> map = Maps.newHashMap();
        apples.forEach(apple -> {
            List<Apple> colorList = Optional.ofNullable(map.get(apple.getColor())).orElseGet(() -> {
                ArrayList<Apple> list = Lists.newArrayList();
                map.put(apple.getColor(), list);
                return list;
            });
            colorList.add(apple);
        });
        return map;
    }

    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        return apples.stream().collect(groupingBy(Apple::getColor));
    }
}
