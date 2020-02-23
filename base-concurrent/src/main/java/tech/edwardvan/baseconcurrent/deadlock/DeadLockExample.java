package tech.edwardvan.baseconcurrent.deadlock;


import lombok.extern.slf4j.Slf4j;

/**
 * 死锁示例
 *
 * @author EdwardVan
 */
@Slf4j
public class DeadLockExample {

    private static Object o1 = new Object();

    private static Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o1) {
                log.info("hello 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("hello 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o2) {
                log.info("hello 3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("hello 4");
                }
            }
        }).start();
    }

}
