package com.foundation;

import java.util.concurrent.ForkJoinPool;

/**
 * <h1>ForkJoinPool</h1>
 *
 * @author zhh 2019-05-14
 */
public class ForkJoinPoolExample {
    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        System.out.println("normal = "+cal());
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer invoke = (Integer) forkJoinPool.invoke(task);
        System.out.println("AccumulatorRecursiveTask = "+invoke);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction = "+AccumulatorRecursiveAction.AccumulatorHelper.getResult());
        AccumulatorRecursiveAction.AccumulatorHelper.rest();
    }

    private static int cal() {
        int sum = 0;
        for (int datum : data) {
            sum += datum;
        }
        return sum;
    }
}
