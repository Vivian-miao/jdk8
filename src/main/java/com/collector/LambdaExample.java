package com.collector;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-28
 */
public class LambdaExample {
    /**
     * this表示当前LambdaExample类的实例
     */
    private Runnable r1 = () -> System.out.println(this);
    private Runnable r2 = new Runnable() {
        @Override
        public void run() {
            // this表示Runnable接口的一个实现类的实例
            System.out.println(this);
        }
    };

    public static void main(String[] args) {
        LambdaExample example = new LambdaExample();
        Thread t1 = new Thread(example.r1);
        Thread t2 = new Thread(example.r2);
        t1.start();
        System.out.println(t1.getName());
        t2.start();
        System.out.println(t2.getName());
    }
}
