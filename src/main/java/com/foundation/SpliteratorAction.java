package com.foundation;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-14
 */
public class SpliteratorAction {

    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(0, 10);
        Spliterator.OfInt spliterator = intStream.spliterator();
        Consumer<Integer> consumer = System.out::println;
        spliterator.forEachRemaining(consumer);

        System.out.println("----------");

        String text = "hello" + "\n" + "java" + "\n" + "jdk" + "\n" + "8" + "\n" + "lambda" + "\n" + "Separate";
        MySpliteratorText mySpliteratorText = new MySpliteratorText(text);
        Optional.of(mySpliteratorText.stream().count()).ifPresent(System.out::println);
        Optional.of(mySpliteratorText.parallelStream().count()).ifPresent(System.out::println);
        System.out.println("----------");
        mySpliteratorText.stream().forEach(System.out::println);
        mySpliteratorText.stream().filter(string -> !"".equals(string)).forEach(System.out::println);
    }

    static class MySpliteratorText {
        private final String[] data;

        MySpliteratorText(String text) {
            Objects.requireNonNull(text, "the parameter can't be null!");
            this.data = text.split("\n");
        }

        private Stream<String> stream() {
            return StreamSupport.stream(new MySpliterator(), false);
        }

        private Stream<String> parallelStream() {
            return StreamSupport.stream(new MySpliterator(), true);
        }

        private class MySpliterator implements Spliterator<String> {
            private int start, end;

            public MySpliterator() {
                this.start = 0;
                this.end = MySpliteratorText.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (start <= end) {
                    action.accept(MySpliteratorText.this.data[start++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if (mid <= 1) {
                    return null;
                }
                int left = start;
                int right = start + mid;
                start = start + mid + 1;
                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            @Override
            public int characteristics() {
                return IMMUTABLE | SIZED | SUBSIZED;
            }
        }
    }
}
