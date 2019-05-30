package com.foundation;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1>lambda 表达式</h1>
 *
 * @author zhh 2019-04-23
 */
public class LambdaExample {
    public static void main(String[] args) {
        Comparator<Apple> comparator = new Comparator<>() {
            @Override
            public int compare(@NotNull Apple o1, @NotNull Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        List<Apple> list = Collections.emptyList();
        list.sort(comparator);

        Comparator<Apple> appleComparator = Comparator.comparing(Apple::getColor);

        Function<String, Integer> function = String::length;
        System.out.println(function);

        Predicate<Apple> appleFilter = a -> "green".equals(a.getColor());
        System.out.println(appleFilter);

        List<String> cityList = Arrays.asList("beijing", "tianjin", "qingdao", "dalian", "shijiazhuang", "wuhan");
        // cityList.sort(String::compareTo);
        Collections.sort(cityList);
        cityList.forEach(System.out::println);

    }
}
