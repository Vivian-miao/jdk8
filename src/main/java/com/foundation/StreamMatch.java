package com.foundation;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * <h1>StreamMatch</h1>
 *
 * @author zhh 2019-04-24
 */
public class StreamMatch {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});

        // 是否匹配所有元素：allMatch
        boolean match = stream.allMatch(integer -> integer > 2);
        // false
        System.out.println(match);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});
        // 是否匹配任一元素：anyMatch
        match = stream.anyMatch(integer -> integer > 2);
        // true
        System.out.println(match);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});
        // 是否未匹配所有元素：noneMatch
        match = stream.noneMatch(integer -> integer > 2);
        // false
        System.out.println(match);

    }
}
