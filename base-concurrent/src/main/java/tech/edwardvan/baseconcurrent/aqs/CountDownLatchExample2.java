package tech.edwardvan.baseconcurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch示例
 *
 * @author EdwardVan
 */
@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch startCountDownLatch = new CountDownLatch(1);
        final CountDownLatch endCountDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    startCountDownLatch.await();
                    log.info("threadNum:{}", threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endCountDownLatch.countDown();
                }
            });
        }

        log.info("让所有线程等待5秒然后执行");
        Thread.sleep(5000);
        startCountDownLatch.countDown();
        endCountDownLatch.await();
        log.info("所有线程执行结束");
        exec.shutdown();
    }
}
