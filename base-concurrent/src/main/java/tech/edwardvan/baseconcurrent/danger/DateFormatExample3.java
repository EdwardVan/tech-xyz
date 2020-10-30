package tech.edwardvan.baseconcurrent.danger;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.Recommend;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SimpleDateFormat并发问题解决方法
 * 使用Java8中的日期类
 *
 * @author EdwardVan
 */
@ThreadSafe
@Recommend
@Slf4j
public class DateFormatExample3 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
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

    private static void update(int i) {
        log.info("第{}次:{}", i, LocalDateTime.parse("2020-08-05 00:00:00", dateTimeFormatter).toString());
    }
}
