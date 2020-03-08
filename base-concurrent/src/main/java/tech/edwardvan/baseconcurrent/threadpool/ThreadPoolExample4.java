package tech.edwardvan.baseconcurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(n)示例
 *
 * @author EdwardVan
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.schedule(() -> log.info("3秒后执行一次"), 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> log.info("每3秒执行一次"), 1, 3, TimeUnit.SECONDS);
    }
}
