package tech.edwardvan.baseconcurrent.danger;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.NotRecommend;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SimpleDateFormat并发问题解决方法
 * 添加锁
 *
 * @author EdwardVan
 */
@ThreadSafe
@NotRecommend("同步执行效率太低")
@Slf4j
public class DateFormatExample4 {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static synchronized void update() {
        try {
            simpleDateFormat.parse("20200202");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
