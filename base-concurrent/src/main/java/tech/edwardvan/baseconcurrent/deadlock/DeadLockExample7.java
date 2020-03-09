package tech.edwardvan.baseconcurrent.deadlock;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决死锁-破坏请求与保持条件
 * 使用ReentrantLock中tryLock方法
 *
 * @author EdwardVan
 */
@Slf4j
public class DeadLockExample7 {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            log.info("{}获取到了锁1", Thread.currentThread().getName());
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    log.info("{}获取到了锁2", Thread.currentThread().getName());
                                    log.info("{}成功获取到了两把锁", Thread.currentThread().getName());
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                log.info("{}尝试获取锁2失败,已重试", Thread.currentThread().getName());
                            }
                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        log.info("{}尝试获取锁1失败,已重试", Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            log.info("{}获取到了锁2", Thread.currentThread().getName());
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    log.info("{}获取到了锁1", Thread.currentThread().getName());
                                    log.info("{}成功获取到了两把锁", Thread.currentThread().getName());
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            } else {
                                log.info("{}尝试获取锁1失败,已重试", Thread.currentThread().getName());
                            }
                        } finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        log.info("{}尝试获取锁2失败,已重试", Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
    }
}
