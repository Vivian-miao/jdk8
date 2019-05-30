package com.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * <h1>自定义 Collector 2</h1>
 *
 * <p>输入Set<T>,输出Map<T,T></p>
 *
 * @author zhh 2019-05-23
 */
public class MyCollector<T> implements Collector<T, Set<T>, Map<T, T>> {


    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoke");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoke");
        return (set, item) -> {
            // 如果要打印 set 集合，EnumSet.of(UNORDERED) 不要有 CONCURRENT，可能会出现 java.util.ConcurrentModificationException 异常
            System.out.println("thread -> " + set + ", " + Thread.currentThread().getName());
            set.add(item);
        };
        // return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        // 并行流才会被调用
        System.out.println("combiner invoke");
        return (set1, set2) -> {
            System.out.println("set1 -> " + set1);
            System.out.println("set2 -> " + set2);
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoke");
        return set -> {
            Map<T, T> map = new TreeMap<>();
            set.forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoke");
        return Collections.unmodifiableSet(EnumSet.of(UNORDERED));
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<String> list = Arrays.asList("hello", "java", "jdk", "lambda", "collector", "jdk", "a", "A", "g");
        // 串行流
        // Map<String, String> collect = list.stream().collect(new MyCollector<>());
        // 并行流
        Map<String, String> collect = list.parallelStream().collect(new MyCollector<>());
        Set<String> set = new HashSet<>(list);
        System.out.println(set);
        System.out.println(collect);
        list.stream().forEach(System.out::println);
    }
}
