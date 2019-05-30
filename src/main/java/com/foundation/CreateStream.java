package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <h1>stream</h1>
 *
 * @author zhh 2019-04-24
 */
public class CreateStream {
    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);

        System.out.println("----------");

        createStreamFromValues().forEach(System.out::println);

        System.out.println("----------");

        createStreamFromArray().forEach(System.out::println);

        System.out.println("----------");

        Stream<String> file = createStreamFromFile();
        System.out.println(file);

        System.out.println("----------");

        createStreamFromIterator().forEach(System.out::println);

        System.out.println("----------");

        createStreamFromGenerate().forEach(System.out::println);

        System.out.println("----------");

        createObjFromGenerate().forEach(System.out::println);

    }

    /**
     * <h2>通过集合创建stream</h2>
     *
     * @return
     */
    private static Stream<String> createStreamFromCollection() {
        // return Arrays.asList("hello", "world", "jdk", "8").stream();
        return Stream.of("hello", "world", "jdk", "8");

    }

    /**
     * <h2>通过Stream.of创建stream</h2>
     *
     * @return
     */
    private static Stream<String> createStreamFromValues() {
        return Stream.of("hello", "world", "jdk", "8");
    }

    /**
     * <h2>通过Arrays.stream创建stream</h2>
     *
     * @return
     */
    private static Stream<String> createStreamFromArray() {
        return Arrays.stream(new String[]{"hello", "world", "jdk", "8"});
    }

    /**
     * <h2>通过File创建stream</h2>
     *
     * @return
     */
    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("E:\\project\\jdk\\jdk8\\src\\main\\java\\com\\foundation\\SimpleStream.java");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h2>通过Iterator创建stream</h2>
     * 无限循环
     *
     * @return
     */
    private static Stream<Integer> createStreamFromIterator() {
        return Stream.iterate(0, num -> num + 2).limit(10);
    }

    /**
     * <h2>通过Generate创建stream</h2>
     * 无限循环
     *
     * @return
     */
    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(10);
    }

    /**
     * <h2>通过Generate创建stream<Obj></></h2>
     *
     * @return
     */
    private static Stream<Obj> createObjFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    /**
     * <h2>stream构建实体类</h2>
     */
    static class ObjSupplier implements Supplier<Obj> {

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            int index = random.nextInt(100);
            return new Obj(index, "name->" + index);
        }
    }

    @Getter
    @Setter
    @ToString
    static class Obj {
        private Integer id;
        private String name;

        private Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
