package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-20
 */
@Getter
@Setter
@ToString
public class Student {
    private String name = "Jack";
    private int age = 18;

    public Student() { }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
