package tech.edwardvan.baseconcurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference示例
 *
 * @author EdwardVan
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceExample {

    private static AtomicReference<String> str = new AtomicReference<>();

    public static void main(String[] args) {
        str.compareAndSet(null, "a");// a
        str.compareAndSet("a", "c"); // c
        str.compareAndSet("a", "b"); // no
        str.compareAndSet("b", "d"); // no
        str.compareAndSet("c", "e"); // e
        log.info("str:{}", str.get());
    }
}
