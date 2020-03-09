package tech.edwardvan.baseconcurrent.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock示例
 * <p>
 * 公平锁和非公平锁
 *
 * @author EdwardVan
 */
@Slf4j
public class ReentrantLockExample3 {

    private static Printer printer = new Printer();

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 10;
    /**
     * 是否公平
     */
    public static boolean isFair = true;

    public static void main(String[] args) throws InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(threadTotal);

        for (int i = 0; i < threadTotal; i++) {
            executorService.execute(() -> {
                log.info("{}线程准备打印", Thread.currentThread().getName());
                printer.print();
                log.info("{}线程打印结束", Thread.currentThread().getName());
            });
            Thread.sleep(100);
        }

    }

    /**
     * 公用打印机
     */
    static class Printer {

        private Lock lock = new ReentrantLock(isFair);

        /**
         * 打印
         */
        public void print() {
            lock.lock();
            try {
                int duration = new Random().nextInt(10) + 1;
                log.info("{}正在打印第一个文件，需要{}秒", Thread.currentThread().getName(), duration);
                Thread.sleep(duration * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                int duration = new Random().nextInt(10) + 1;
                log.info("{}正在打印第二个文件，需要{}秒", Thread.currentThread().getName(), duration);
                Thread.sleep(duration * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
