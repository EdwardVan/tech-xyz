package tech.edwardvan.baseconcurrent.atomic;

import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference示例
 */
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<String> str = new AtomicReference<>("a");

    public static void main(String[] args) {
        str.compareAndSet("a", "c"); // c
        str.compareAndSet("a", "b"); // no
        str.compareAndSet("b", "d"); // no
        str.compareAndSet("c", "e"); // e
        System.out.println("str:" + str.get());
    }
}
