package tech.edwardvan.baseconcurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock Condition示例
 */
@Slf4j
public class ReentrantLockExample4 {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("{}获取到锁", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                log.info("{}开始等待", Thread.currentThread().getName());
                condition.await();
                log.info("{}得到通知", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                log.info("{}释放锁", Thread.currentThread().getName());
            }
        }).start();

        Thread.sleep(500);

        new Thread(() -> {
            reentrantLock.lock();
            log.info("{}获取到锁", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                condition.signalAll();
                log.info("{}发送通知", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                log.info("{}释放锁", Thread.currentThread().getName());
            }
        }).start();
    }
}
