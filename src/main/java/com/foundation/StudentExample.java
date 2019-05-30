package com.foundation;

import java.util.function.Supplier;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-20
 */
public class StudentExample {
    public static void main(String[] args) {
        Supplier<Student> supplier = Student::new;
        System.out.println(supplier.get().getAge());
    }
}
