package tech.edwardvan.baseconcurrent.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * wait、notify方法介绍
 *
 * @author EdwardVan
 */
@Slf4j
public class WaitNotifyExample {
    public static void main(String[] args) throws InterruptedException {

        final Object lock = new Object();

        final Thread thread1 = new Thread(() -> {
            log.info("{}开始执行", Thread.currentThread().getName());
            synchronized (lock) {
                log.info("{}获得锁", Thread.currentThread().getName());
                try {
                    log.info("0秒钟后,{}执行Thread.sleep(2000)", Thread.currentThread().getName());
                    Thread.sleep(2000);
                    log.info("2秒钟后,{}执行lock.wait()", Thread.currentThread().getName());
                    lock.wait();
                    log.info("6秒钟后,{}重新获得锁", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread.sleep(100);
        final Thread thread2 = new Thread(() -> {
            log.info("{}开始执行", Thread.currentThread().getName());
            synchronized (lock) {
                log.info("2秒钟后,{}获得锁", Thread.currentThread().getName());
                try {
                    log.info("2秒钟后,{}执行Thread.sleep(2000)", Thread.currentThread().getName());
                    Thread.sleep(2000);
                    log.info("4秒钟后,{}执行lock.notify()", Thread.currentThread().getName());
                    lock.notify();
                    log.info("4秒钟后,{}执行Thread.sleep(2000)", Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("6秒钟后,{}释放锁", Thread.currentThread().getName());
            }
        });

        thread2.start();
        Thread.sleep(1000);
        log.info("1秒钟后,{}的线程状态为:{}", thread1.getName(), thread1.getState());
        log.info("1秒钟后,{}的线程状态为:{}", thread2.getName(), thread2.getState());
        Thread.sleep(2000);
        log.info("3秒钟后,{}的线程状态为:{}", thread1.getName(), thread1.getState());
        log.info("3秒钟后,{}的线程状态为:{}", thread2.getName(), thread2.getState());
        Thread.sleep(2000);
        log.info("5秒钟后,{}的线程状态为:{}", thread1.getName(), thread1.getState());
        log.info("5秒钟后,{}的线程状态为:{}", thread2.getName(), thread2.getState());
    }
}
