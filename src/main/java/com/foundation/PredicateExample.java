package com.foundation;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-17
 */
public class PredicateExample {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 5;
        // true
        System.out.println(predicate.test("lambda"));
        System.out.println("----------");

        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        conditionFilter(integers, integer -> integer % 2 == 0);
        System.out.println("----------");
        Optional.of(integers.stream().filter(integer -> integer % 2 != 0).collect(Collectors.toList())).ifPresent(System.out::println);
        System.out.println("----------");
        conditionFilter(integers, integer -> integer > 5);
        System.out.println("----------");
        conditionFilter(integers, integer -> true);
        System.out.println("----------");
        conditionFilterByAnd(integers, integer -> integer > 5, integer -> integer % 2 == 0);
        System.out.println("----------");
        conditionFilterByOr(integers, integer -> integer > 5, integer -> integer % 2 == 0);
        System.out.println("----------");
        conditionFilterByNegate(integers, integer -> integer > 5, integer -> integer % 2 == 0);
        System.out.println("----------");
        System.out.println(isEqual("test").test("test"));

    }

    private static void conditionFilter(List<Integer> integers, Predicate<Integer> predicate) {
        for (Integer integer : integers) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private static void conditionFilterByAnd(List<Integer> integers, Predicate<Integer> predicate, Predicate<Integer> predicate2) {
        for (Integer integer : integers) {
            if (predicate.and(predicate2).test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private static void conditionFilterByOr(List<Integer> integers, Predicate<Integer> predicate, Predicate<Integer> predicate2) {
        for (Integer integer : integers) {
            if (predicate.or(predicate2).test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private static void conditionFilterByNegate(List<Integer> integers, Predicate<Integer> predicate, Predicate<Integer> predicate2) {
        for (Integer integer : integers) {
            if (predicate.and(predicate2).negate().test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private static Predicate<String> isEqual(Object object) {
        return Predicate.isEqual(object);
    }
}
