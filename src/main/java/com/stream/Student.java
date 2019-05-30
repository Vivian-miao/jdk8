package com.stream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>学生类</h1>
 *
 * @author zhh 2019-05-22
 */
@Getter
@Setter
@ToString
class Student {
    private String name;
    private int age;
    private int score;

    Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
}
