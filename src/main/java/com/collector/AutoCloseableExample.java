package com.collector;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-24
 */
public class AutoCloseableExample implements AutoCloseable {
    private void doSomething() {
        System.out.println("do something invoked!");
    }
    @Override
    public void close(){
        System.out.println("close invoked!");
    }

    public static void main(String[] args) {
        try (AutoCloseableExample example = new AutoCloseableExample()) {
            example.doSomething();
        }
    }
}
