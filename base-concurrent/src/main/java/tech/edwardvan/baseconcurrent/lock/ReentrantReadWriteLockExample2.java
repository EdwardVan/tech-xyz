package tech.edwardvan.baseconcurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock示例
 * <p>
 * 锁的降级
 *
 * @author EdwardVan
 */
@Slf4j
public class ReentrantReadWriteLockExample2 {


    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final static Lock readLock = lock.readLock();

    private final static Lock writeLock = lock.writeLock();

    public static void main(String[] args) {
        writeLock.lock();
        try {
            log.info("{}获取到了写锁", Thread.currentThread().getName());
            readLock.lock();
            try {
                log.info("{}在不释放写锁的情况下,直接获取读锁,成功降级", Thread.currentThread().getName());
            } finally {
                readLock.unlock();
            }
        } finally {
            writeLock.unlock();
        }
    }
}
