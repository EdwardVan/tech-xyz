package tech.edwardvan.baseconcurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized之对象锁
 * <p>
 * 优点:
 * 实现简单,语义清晰
 * 加锁解锁过程由JVM自动控制,提供了多种优化方案
 * <p>
 * 缺点:
 * 锁的释放情况少,只在程序正常执行完成和抛出异常时释放锁
 * 试图获得锁时不能设定超时
 * 不能中断一个正在试图获得锁的线程,只能选择等待或者阻塞,直到别的线程释放这个锁
 * 无法知道是否成功获取到锁
 *
 * @author EdwardVan
 */
@Slf4j
public class SynchronizedExample {

    Object lock = new Object();

    /**
     * 修饰一个代码块
     */
    public void test1() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                log.info(Integer.toString(i));
            }
        }
    }

    /**
     * 修饰一个成员方法
     */
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info(Integer.toString(i));
        }
    }

    public static void main(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.execute(() -> example1.test1());
        //executorService.execute(() -> example1.test1());

        executorService.execute(() -> example1.test1());
        executorService.execute(() -> example2.test1());
        executorService.shutdown();
    }
}
