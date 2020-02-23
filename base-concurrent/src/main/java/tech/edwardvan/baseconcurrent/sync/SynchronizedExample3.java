package tech.edwardvan.baseconcurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized之异常情况
 * <p>
 * 遇到异常以后jvm会自动释放锁
 *
 * @author EdwardVan
 */
@Slf4j
public class SynchronizedExample3 {

    Object lock = new Object();

    public void test1() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                if (i == 5 && Thread.currentThread().getName().equals("pool-1-thread-1")) {
                    throw new RuntimeException();
                }
                log.info(Integer.toString(i));
            }
        }
    }


    public static void main(String[] args) {
        SynchronizedExample3 example1 = new SynchronizedExample3();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example1.test1());
        executorService.execute(() -> example1.test1());
        executorService.shutdown();
    }
}
