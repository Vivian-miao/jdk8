package com.foundation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * <h1>自定义Collector类测试</h1>
 *
 * @author zhh 2019-05-14
 */
public class CustomerCollectorAction {
    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

        String[] array = new String[]{"hello", "java", "jdk", "8", "lambda", "collector", "string"};
        List<String> list = Arrays.stream(array)
                .filter(string -> string.length() > 4)
                .collect(collector);
        System.out.println(list);

        System.out.println("----------");

        // 并行
        List<String> stringList = Arrays.asList("hello", "java", "jdk", "8", "lambda", "collector", "string");
        List<String> collect = stringList.parallelStream().filter(string -> string.length() > 4)
                .collect(collector);
        System.out.println(collect);
    }

}
