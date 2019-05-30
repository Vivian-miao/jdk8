package com.foundation;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1>NumericStream</h1>
 *
 * @author zhh 2019-04-28
 */
public class NumericStream {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 4, 3, 2, 1});
        Integer result = stream.filter(integer -> integer > 2).reduce(0, Integer::sum);
        System.out.println(result);

        // int(4byte/32bit)
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 4, 3, 2, 1});
        int sum = stream.mapToInt(Integer::intValue).filter(integer -> integer > 2).sum();
        // int sum = stream.mapToInt(Integer::intValue).filter(integer -> integer > 2).reduce(0, Integer::sum);
        System.out.println(sum);

        int a = 9;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

        IntStream.rangeClosed(1, 100)
                .filter(b -> (Math.sqrt(a * a + b * b) % 1) == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));
    }
}
