package com.foundation;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <h1>并行进程</h1>
 *
 * @author zhh 2019-05-14
 */
public class ParallelProcessing {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("normalAdd process time = " + measureSumPerformance(ParallelProcessing::normalAdd, 10000000) + " ms");
        System.out.println("iteratorStream process time = " + measureSumPerformance(ParallelProcessing::iteratorStream, 10000000) + " ms");
        System.out.println("parallelStream process time = " + measureSumPerformance(ParallelProcessing::parallelStream, 10000000) + " ms");
        System.out.println("parallelStream2 process time = " + measureSumPerformance(ParallelProcessing::parallelStream2, 10000000) + " ms");
        System.out.println("parallelStream3 process time = " + measureSumPerformance(ParallelProcessing::parallelStream3, 10000000) + " ms");

    }

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long faster = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
            // System.out.println("the sum result=" + result);
            if (duration < faster) {
                faster = duration;
            }
        }
        return faster;
    }

    /**
     * 串行
     */
    private static long iteratorStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    /**
     * 并行
     */
    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue)
                .parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit) {
        return LongStream.rangeClosed(1, limit).parallel().sum();
    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (long i = 1L; i < limit; i++) {
            result += i;
        }
        return result;
    }
}
