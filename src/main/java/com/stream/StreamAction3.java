package com.stream;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <h1>并行流、串行流</h1>
 *
 * @author zhh 2019-05-21
 */
public class StreamAction3 {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>(5000000);

        for (int i = 0; i < 5000000; ++i) {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("begin sort");
        // 纳秒
        long startTime = System.nanoTime();
        // serialStream(list);
        parallelStream(list);
        long endTime = System.nanoTime();
        System.out.println("sort time: "+TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
    }

    /**
     * 串行流
     */
    private static void serialStream(ArrayList<Object> list) {
        list.stream().sorted();
    }

    /**
     * 并行流
     */
    private static void parallelStream(ArrayList<Object> list) {
        list.parallelStream().sorted();
    }
}
