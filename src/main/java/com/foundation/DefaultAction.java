package com.foundation;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class DefaultAction {
    private void confuse(Object object) {
        System.out.println("object = " + object);
    }

    private void confuse(int[] ints) {
        System.out.println("int[] = " + ints);
    }

    public static void main(String[] args) {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());

        DefaultAction action = new DefaultAction();
        // int[] = null，有明确的参数类型
        action.confuse(null);
        int[] ints = null;
        Object object = ints;
        // object = null
        action.confuse(object);

        D d = new D();
        // hello D
        d.hello();
        F f = new F();
        // hello E
        f.hello();
    }

    private interface A {
        /**
         * 大小
         *
         * @return
         */
        int size();

        /**
         * 判空
         *
         * @return
         */
        default boolean isEmpty() {
            return size() == 0;
        }
    }

    // 上级接口次之
    private interface B{
        default void hello() {
            System.out.println("hello B");
        }
    }

    // 优先级最高
    private interface C extends B{
        @Override
        default void hello() {
            System.out.println("hello C");
        }
    }

    private static class D implements B,C{
        @Override
        public void hello() {
            System.out.println("hello D");
        }
    }

    private interface E{
        default void hello() {
            System.out.println("hello E");
        }
    }

    private static class F implements B,E{
        @Override
        public void hello() {
            E.super.hello();
        }
    }
}
