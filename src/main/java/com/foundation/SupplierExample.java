package com.foundation;

import java.util.function.Supplier;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-20
 */
public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello";
        System.out.println(supplier.get());
    }
}
