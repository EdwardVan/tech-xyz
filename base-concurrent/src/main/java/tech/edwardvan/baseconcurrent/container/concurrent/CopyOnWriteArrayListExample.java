package tech.edwardvan.baseconcurrent.container.concurrent;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;
import tech.edwardvan.baseconcurrent.danger.ArrayListExample;

import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList示例
 * 不能用于实时读,读不加锁写加锁,读写分离思想
 * 适合读多写少的场景
 * <p>
 * {@link ArrayListExample}
 *
 * @author EdwardVan
 */
@ThreadSafe
@Slf4j
public class CopyOnWriteArrayListExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
        log.info("size:{}", list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
