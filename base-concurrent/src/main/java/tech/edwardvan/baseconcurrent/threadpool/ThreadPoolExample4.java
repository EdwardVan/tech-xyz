package tech.edwardvan.baseconcurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(n)示例
 */
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.schedule(() -> System.out.println("3秒后执行一次"), 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> System.out.println("每3秒执行一次"), 1, 3, TimeUnit.SECONDS);
    }
}
