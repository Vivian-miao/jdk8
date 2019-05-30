package com.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * <h1>lambda 表达式</h1>
 *
 * @author zhh 2019-04-23
 */
public class LambdaUsage {
    public static void main(String[] args) {
        Runnable runnable1 = () -> System.out.println("runnable1");

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable2");
            }
        };

        process(runnable1);
        process(runnable2);
        process(() -> System.out.println("runnable3"));

        List<Apple> apples = Arrays.asList(new Apple("green", 80), new Apple("blue", 90), new Apple("green", 60));

        List<Apple> list = filter(apples, apple -> "green".equals(apple.getColor()));
        System.out.println(list);

        List<Apple> result = filterByWeight(apples, weight -> weight > 60);
        System.out.println(result);

        List<Apple> biParams = filterByBiPredicate(apples, (color, weight) -> "green".equals(color) && weight > 60);
        System.out.println(biParams);

        System.out.println("---------------");

        filterConsumer(apples, System.out::println);

        System.out.println("---------------");
        filterBiConsumer("color", apples, (apple, color) -> System.out.println(color + "=>" + apple.getColor() + ",weight=>" + apple.getWeight()));

        System.out.println("---------------");

        String resultFun = testFunction(new Apple("red", 100), Apple::toString);
        System.out.println(resultFun);

        System.out.println("---------------");

        IntFunction<Double> intFunction = num -> num * 100.0d;
        System.out.println(intFunction.apply(5));

        System.out.println("---------------");

        Apple apple = testBiFunction("red", 130, Apple::new);
        System.out.println(apple);

        System.out.println("---------------");

        // method inference
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get().getClass());

        System.out.println("---------------");

        Apple apple1 = testSupplier(() -> new Apple("green", 120));
        System.out.println(apple1);
    }

    private static void process(Runnable runnable) {
        runnable.run();
    }

    /**
     * <h2>过滤器</h2>
     * <p>Predicate<T></p>
     *
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * <h2>LongPredicate</h2>
     *
     * @param apples
     * @param predicate
     * @return
     */
    private static List<Apple> filterByWeight(List<Apple> apples, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * <h2>BiPredicate<T, U></h2>
     *
     * @param apples
     * @param predicate
     * @return
     */
    private static List<Apple> filterByBiPredicate(List<Apple> apples, BiPredicate<String, Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getColor(), apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * <h2>Consumer<T></h2>
     *
     * @param apples
     * @param consumer
     * @return
     */
    private static void filterConsumer(List<Apple> apples, Consumer<Apple> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple);
        }
    }

    /**
     * <h2>BiConsumer<T, U></h2>
     *
     * @param str
     * @param apples
     * @param consumer
     * @return
     */
    private static void filterBiConsumer(String str, List<Apple> apples, BiConsumer<Apple, String> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple, str);
        }
    }

    /**
     * <h2>Function<T, R></h2>
     *
     * @param apple
     * @param function
     * @return
     */
    private static String testFunction(Apple apple, Function<Apple, String> function) {
        return function.apply(apple);
    }

    /**
     * <h2>BiFunction<T, U, R></h2>
     *
     * @param color
     * @param weight
     * @param function
     * @return
     */
    private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> function) {
        return function.apply(color, weight);
    }

    /**
     * <h2>Supplier<T></h2>
     * @param supplier
     * @return
     */
    private static Apple testSupplier(Supplier<Apple> supplier) {
        return supplier.get();
    }
}
