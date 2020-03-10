package tech.edwardvan.baseconcurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong示例
 *
 * @author EdwardVan
 */
@ThreadSafe
@Slf4j
public class AtomicLongExample {

    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.getAndIncrement();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        long endTime = System.currentTimeMillis();
        log.info("count:{},消耗时长:{}", count.get(), endTime - startTime);
    }
}
