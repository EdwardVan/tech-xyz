package tech.edwardvan.basejava.nested;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态内部类
 * 1. 要创建静态内部类的对象,并不需要其外围类的对象.
 * 2. 不能从静态内部类的对象中访问非静态的外围类对象.
 */
@Slf4j
public class StaticNestedClassesExample {
    private static int outer_i = 1;
    private int outer_j = 10;

    public static void outer_f1() {
    }

    public void outer_f2() {
    }

    /**
     * 静态内部类可以用public,protected,private修饰
     * 静态内部类中可以定义静态或者非静态的成员
     */
    private static class Inner {
        static int inner_i = 100;
        int inner_j = 200;

        static void inner_f1() {
            // 静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
            log.info("inner_f1->outer_i:{}", outer_i);
            outer_f1();
        }

        void inner_f2() {
            // 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)
            //log.info("inner_f2->outer_j:{}", j);
            // outer_f2();
            log.info("inner_f2->inner_j:{}", inner_j);
        }
    }

    public void outer_f3() {
        // 外部类访问内部类的静态成员
        log.info("outer_f3->inner_i:{}", Inner.inner_i);
        Inner.inner_f1();
        // 外部类访问内部类的非静态成员:实例化内部类即可
        Inner inner = new Inner();
        inner.inner_f2();
    }

    public static void main(String[] args) {
        new StaticNestedClassesExample().outer_f3();
    }
}
