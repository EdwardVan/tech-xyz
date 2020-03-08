package tech.edwardvan.baseconcurrent.danger;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.Recommend;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat并发问题解决方法
 * <p>
 * ThreadLocal原理:每一个Thread类中都有一个ThreadLocalMap,ThreadLocalMap中保存键值对,键为ThreadLocal
 *
 * @author EdwardVan
 */
@ThreadSafe
@Recommend
@Slf4j
public class DateFormatExample5 {

    public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        //与前面例子不同,此处使用newFixedThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    update();
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

    private static void update() {
        try {
            simpleDateFormatThreadLocal.get().parse("20200202");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
