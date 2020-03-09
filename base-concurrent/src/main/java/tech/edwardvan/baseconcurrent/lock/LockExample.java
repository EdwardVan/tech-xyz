package tech.edwardvan.baseconcurrent.lock;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * synchronized示例
 * <p>
 * 缺点
 * 效率低:锁的释放情况少 试图获得锁时不能设定超时 不能中断一个正在试图获得锁的线程
 * 不够灵活(读写锁更灵活):加锁和释放的时机单一,每个锁仅有单一的条件(某个对象),可能是不够的
 * 无法知道是否成功获取到锁
 *
 * @author EdwardVan
 */
@ThreadSafe
@Slf4j
public class LockExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
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
        log.info("count:{}", count);
    }

    private synchronized static void add() {
        count++;
    }
}
