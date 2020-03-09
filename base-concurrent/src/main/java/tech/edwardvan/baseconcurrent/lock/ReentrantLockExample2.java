package tech.edwardvan.baseconcurrent.lock;


import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock示例
 * <p>
 * lockInterruptibly方法
 *
 * @author EdwardVan
 */
@Slf4j
public class ReentrantLockExample2 {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                //获取可中断锁
                lock.lockInterruptibly();
                try {
                    log.info("{}获取到了锁", Thread.currentThread().getName());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.info("{}睡眠期间被中断", Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                    log.info("{}释放锁", Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                log.info("{}获得锁期间被中断", Thread.currentThread().getName());
            }
        }, "线程一");


        Thread thread2 = new Thread(() -> {
            //获取可中断锁
            try {
                lock.lockInterruptibly();
                try {
                    log.info("{}获取到了锁", Thread.currentThread().getName());
                    Thread.sleep(100000);
                } finally {
                    lock.unlock();
                    log.info("{}释放锁", Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程二");
//        thread2.start();
//        Thread.sleep(500);
        thread1.start();
        Thread.sleep(2000);
        thread1.interrupt();
    }

}
