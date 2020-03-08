package tech.edwardvan.baseconcurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newFixedThreadPool(n)示例
 *
 * @author EdwardVan
 */
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("task:{}", index);
            });
        }
        executorService.shutdown();
    }
}
