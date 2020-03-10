package tech.edwardvan.baseconcurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater示例
 * 注意:
 * 1. 可见范围
 * 2. 不支持static
 *
 * @author EdwardVan
 */
@Slf4j
public class AtomicIntegerFieldUpdaterExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicIntegerFieldUpdater<Person> updaterMoney = AtomicIntegerFieldUpdater.newUpdater(Person.class, "money");

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        final Person person1 = new Person();
        final Person person2 = new Person();

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    person1.money++;
                    updaterMoney.getAndIncrement(person2);
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
        log.info("person1:{},person2:{}", person1.money, person2.money);
    }

    public static class Person {
        volatile int money = 0;
    }

}
