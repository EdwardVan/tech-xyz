package tech.edwardvan.baseconcurrent.container.concurrent;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;
import tech.edwardvan.baseconcurrent.danger.HashMapExample;

import java.util.Map;
import java.util.concurrent.*;

/**
 * ConcurrentHashMap示例
 * <p>
 * {@link HashMapExample}
 *
 * @author EdwardVan
 */
@ThreadSafe
@Slf4j
public class ConcurrentHashMapExample {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

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
        log.info("size:{}", map.size());
    }

    private static void update(int i) {
        map.put(i, i);
    }
}
