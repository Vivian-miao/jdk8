package com.joda;

import org.joda.primitives.list.IntList;
import org.joda.primitives.list.impl.ArrayIntList;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-29
 */
public class JodaPrimitives {
    public static void main(String[] args) {
        IntList integers = new ArrayIntList();
        integers.add(1);
        integers.add(2);
        integers.forEach(System.out::println);
    }
}
