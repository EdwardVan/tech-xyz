package tech.edwardvan.baseconcurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore示例
 *
 * @author EdwardVan
 */
@Slf4j
public class SemaphoreExample4 {

    private final static int threadCount = 20;

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) { // 尝试获取一个许可
                        log.info("threadNum:{}", threadNum);
                        Thread.sleep(1000);
                        semaphore.release(); // 释放一个许可
                    } else {
                        log.info("获取一个许可失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }
}
