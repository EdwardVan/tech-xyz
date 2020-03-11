package tech.edwardvan.baseconcurrent.container.concurrent;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.NotThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

/**
 * ConcurrentHashMap示例
 *
 * @author EdwardVan
 */
@NotThreadSafe
@Slf4j
public class ConcurrentHashMapExample2 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        map.put(1, 0);

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
        log.info("1 :{}", map.get(1));
    }

    private static void update() {
        //线程安全操作+线程安全操作 不一定是线程安全操作
        Integer i = map.get(1);
        int newI = i + 1;
        map.put(1, newI);
    }
}
