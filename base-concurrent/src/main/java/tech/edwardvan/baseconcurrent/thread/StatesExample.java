package tech.edwardvan.baseconcurrent.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程的状态
 *
 * @author EdwardVan
 */
@Slf4j
public class StatesExample {
    public static void main(String[] args) throws InterruptedException {
//        newStates();
//        runnableStates();
//        terminatedStates();
//        blockedAndTimedWaitingStates();
        waitingStates();
    }

    public static void newStates() {
        log.info("newStates方法开始");
        Thread thread = new Thread(() -> log.info(Thread.currentThread().getState().toString()));
        log.info(thread.getState().toString());
    }

    public static void runnableStates() {
        log.info("runnableStates方法开始");
        new Thread(() -> log.info(Thread.currentThread().getState().toString())).start();
    }

    public static void terminatedStates() throws InterruptedException {
        log.info("terminatedStates方法开始");
        Thread thread = new Thread(() -> log.info(Thread.currentThread().getState().toString()));
        thread.start();
        thread.join();
        log.info(thread.getState().toString());
    }

    public static void blockedAndTimedWaitingStates() throws InterruptedException {
        log.info("blockedAndTimedWaitingStates方法开始");
        final Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                log.info("thread1获得锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                log.info("thread2获得锁");
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        log.info("thread1的状态为{}", thread1.getState().toString());
        log.info("thread2的状态为{}", thread2.getState().toString());
    }

    public static void waitingStates() throws InterruptedException {
        log.info("waitingStates方法开始");
        final Object lock = new Object();
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        log.info("thread is Interrupted: {}", Thread.currentThread().isInterrupted());
                    }
                }
            }
        };
        thread.start();
        Thread.sleep(1000);
        log.info("thread的状态为{}", thread.getState().toString());
        thread.interrupt();
    }
}
