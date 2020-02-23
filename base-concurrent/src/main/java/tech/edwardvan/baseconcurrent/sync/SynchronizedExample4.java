package tech.edwardvan.baseconcurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized之可重入锁
 * <p>
 * 优点:
 * 避免死锁
 * 提升了封装性
 *
 * @author EdwardVan
 */
@Slf4j
public class SynchronizedExample4 {

    /**
     * 第一次获得锁
     */
    public static void test1() {
        synchronized (SynchronizedExample4.class) {
            log.info("第一次获得锁");
            test2();
        }
    }

    /**
     * 第二次获得锁
     */
    public static synchronized void test2() {
        log.info("第二次获得锁");
    }

    public static void main(String[] args) {
        SynchronizedExample4.test1();
    }
}
