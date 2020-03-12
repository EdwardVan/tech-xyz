package tech.edwardvan.baseconcurrent.container.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 阻塞队列
 * LinkedBlockingQueue中的有两把锁,提高了效率
 *
 * @author EdwardVan
 */
@Slf4j
public class BlockingQueueExample2 {

    /**
     * 阻塞队列
     */
    private static BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {

        double random = Math.random();
        System.out.println(random);

        Runnable producerRunnable = () -> {
            while (true) {
                try {
                    log.info("{}生产了一个产品", Thread.currentThread().getName());
                    blockingQueue.put(new Object());
                    //随机休眠模拟行为消耗的时间
                    Thread.sleep((long) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable consumerRunnable = () -> {
            while (true) {
                try {
                    blockingQueue.take();
                    log.info("{}消费了一个产品", Thread.currentThread().getName());
                    //随机休眠模拟行为消耗的时间
                    Thread.sleep((long) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService producerService = Executors.newFixedThreadPool(3);
        ExecutorService consumerService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            producerService.execute(producerRunnable);
            consumerService.execute(consumerRunnable);
        }
        producerService.shutdown();
        consumerService.shutdown();
    }
}
