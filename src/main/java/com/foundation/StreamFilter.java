package com.foundation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>StreamFilter</h1>
 *
 * @author zhh 2019-04-24
 */
public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 4);
        // 筛选filter
        List<Integer> collect = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);

        // 去重distinct,去掉重复的结果
        collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);

        // 跳过skip,跳过流的前n个元素
        collect = list.stream().skip(3).collect(Collectors.toList());
        System.out.println(collect);

        // 截取limit,截取流的前N个元素
        collect = list.stream().limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }
}
