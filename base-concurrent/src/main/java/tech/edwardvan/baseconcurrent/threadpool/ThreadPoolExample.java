package tech.edwardvan.baseconcurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newCachedThreadPool()示例
 *
 * @author EdwardVan
 */
@Slf4j
public class ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

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
        log.info("是否真正停止:{}", executorService.isTerminated());
        Thread.sleep(500);
        List<Runnable> runnables = executorService.shutdownNow();
        log.info("是否真正停止:{}", executorService.isTerminated());
    }
}
