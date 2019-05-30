package com.collector;

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
    private int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
