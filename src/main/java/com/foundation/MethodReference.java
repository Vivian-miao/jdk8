package com.foundation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <h1>方法推导</h1>
 *
 * @author zhh 2019-04-24
 */
public class MethodReference {

    public static void main(String[] args) {
        Consumer<String> stringConsumer = System.out::println;
        useConsumer(stringConsumer, "jdk8");

        useConsumer(System.out::println, "jdk8");

        List<Apple> apples = Arrays.asList(new Apple("green", 80), new Apple("cyan", 90), new Apple("red", 100));
        System.out.println(apples);
        /*apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        });*/
        // apples.sort((o1, o2) -> o1.getColor().compareTo(o2.getColor()));
        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println(apples);

        System.out.println("-----------");

        apples.forEach(System.out::println);

        System.out.println("-----------");

        Function<String, Integer> function = Integer::parseInt;
        System.out.println(function.apply("123"));

        System.out.println("-----------");

        BiFunction<String, Integer, Character> biFunction = String::charAt;
        System.out.println(biFunction.apply("jdk8", 2));

        System.out.println("-----------");

        String string = "jdk8";
        Function<Integer, Character> characterFunction = string::charAt;
        System.out.println(characterFunction.apply(0));

        System.out.println("-----------");

        Supplier<String> supplier = String::new;
        System.out.println(supplier.get());

        System.out.println("-----------");

        BiFunction<String, Long, Apple> aNew = Apple::new;
        System.out.println(aNew.apply("red", 100L));
    }

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }
}
