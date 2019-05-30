package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * <h1>groupingBy and partitioningBy</h1>
 *
 * @author zhh 2019-05-22
 */
public class StreamGroupingBy {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("mike", 14, 90),
                new Student("jean", 16, 95),
                new Student("jack", 15, 95),
                new Student("jean", 16, 100)
        );
        groupByName(students);
        System.out.println("----------");
        groupByScore(students);
        System.out.println("----------");
        countGroupByName(students);
        System.out.println("----------");
        averageScoreGroupByName(students);
        System.out.println("----------");
        partitionByScore(students);
    }

    /**
     * 根据姓名分组
     */
    private static void groupByName(List<Student> students) {
        Map<String, List<Student>> collect = students.stream().collect(groupingBy(Student::getName));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
     * 根据分数分组
     */
    private static void groupByScore(List<Student> students) {
        Map<Integer, List<Student>> collect = students.stream().collect(groupingBy(Student::getScore));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
     * 根据姓名分组，并计数
     */
    private static void countGroupByName(List<Student> students) {
        Map<String, Long> collect = students.stream().collect(groupingBy(Student::getName, counting()));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
     * 根据姓名分组，并计算分数平均值
     */
    private static void averageScoreGroupByName(List<Student> students) {
        Map<String, Double> collect = students.stream().collect(groupingBy(Student::getName, averagingDouble(Student::getScore)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
     * 根据 score>95 的进行分区
     */
    private static void partitionByScore(List<Student> students) {
        Map<Boolean, List<Student>> collect = students.stream().collect(partitioningBy(student -> student.getScore() >= 95));
        Optional.of(collect).ifPresent(System.out::println);
    }
}
