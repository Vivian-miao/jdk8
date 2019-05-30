package com.collector;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * <h1>collect usage</h1>
 *
 * @author zhh 2019-05-22
 */
public class CollectorAction {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("mike", 80),
                new Student("john", 85),
                new Student("lucy", 70),
                new Student("jenny", 98),
                new Student("jenny", 98)
        );
        countStudent(students);
        System.out.println("----------");
        getStudentByMinScore(students);
        System.out.println("----------");
        getAverageScore(students);
        System.out.println("----------");
        getSumScore(students);
        System.out.println("----------");
        getSummarizingInt(students);
        System.out.println("----------");
        joinName(students);
        System.out.println("----------");
        groupingByScoreAndName(students);
        System.out.println("----------");
        partitionByScore(students);
        System.out.println("----------");
        partitionByScore2(students);
        System.out.println("----------");
        partitionByScoreAndCount(students);
        System.out.println("----------");
        groupingByNameAndScore(students);
        System.out.println("----------");

    }

    /**
     * 计算学生个数
     */
    private static void countStudent(List<Student> students) {
        // students.stream().count();
        int size = students.size();
        Optional.of(size).ifPresent(System.out::println);
    }

    /**
     * 找出分数最低/最高的学生
     */
    private static void getStudentByMinScore(List<Student> students) {
        students.stream().min(Comparator.comparingInt(Student::getScore)).ifPresent(System.out::println);
        students.stream().max(Comparator.comparingInt(Student::getScore)).ifPresent(System.out::println);
    }

    /**
     * 求分数的平均值
     */
    private static void getAverageScore(List<Student> students) {
        Optional.of(students.stream().collect(averagingDouble(Student::getScore))).ifPresent(System.out::println);
    }

    /**
     * 分数求和
     */
    private static void getSumScore(List<Student> students) {
        Optional.of(students.stream().mapToInt(Student::getScore).sum()).ifPresent(System.out::println);
    }

    /**
     * 分数信息汇总
     */
    private static void getSummarizingInt(List<Student> students) {
        Optional.of(students.stream().collect(Collectors.summarizingInt(Student::getScore))).ifPresent(System.out::println);
    }

    /**
     * 姓名拼接
     */
    private static void joinName(List<Student> students) {
        Optional.ofNullable(students.stream().map(Student::getName).collect(joining(", "))).ifPresent(System.out::println);
        Optional.ofNullable(students.stream().map(Student::getName).collect(joining(", ", "[", "]"))).ifPresent(System.out::println);
    }

    /**
     * 根据分数分组后，再根据名字、分数分组
     */
    private static void groupingByScoreAndName(List<Student> students) {
        Map<Integer, Map<String, List<Student>>> collect = students.stream().collect(groupingBy(Student::getScore, groupingBy(Student::getName)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * 根据 score>80 的进行分区
     */
    private static void partitionByScore(List<Student> students) {
        Map<Boolean, List<Student>> collect = students.stream().collect(partitioningBy(student -> student.getScore() > 80));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
     * 根据 score>80 的进行分区，再根据 score>=85 的进行分区
     */
    private static void partitionByScore2(List<Student> students) {
        Map<Boolean, Map<Boolean, List<Student>>> collect = students.stream().collect(partitioningBy(student -> student.getScore() > 80, partitioningBy(student -> student.getScore() > 85)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    /**
     * 根据 score>80 的进行分区,并算出个数
     */
    private static void partitionByScoreAndCount(List<Student> students) {
        Optional.of(students.stream().collect(partitioningBy(student -> student.getScore() > 80, counting()))).ifPresent(System.out::println);
    }

    /**
     * 根据名字分组后，再根据名字、分数分组
     */
    private static void groupingByNameAndScore(List<Student> students) {
        // students.stream().collect(groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingInt(Student::getScore)), Optional::get)));
        Map<String, Student> collect = students.stream().collect(toMap(Student::getName, Function.identity(), BinaryOperator.minBy(Comparator.comparingInt(Student::getScore))));
        Optional.of(collect).ifPresent(System.out::println);
    }

}
