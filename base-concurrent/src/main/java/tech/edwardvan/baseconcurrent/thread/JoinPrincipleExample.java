package tech.edwardvan.baseconcurrent.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * join原理
 * <p>
 * 结论:不建议用Thread对象当锁对象,因为线程执行结束时,会自动调用当前线程对象的notifyAll()
 *
 * @author EdwardVan
 */
@Slf4j
public class JoinPrincipleExample {

    public static void main(String[] args) throws InterruptedException {
        final Thread main = Thread.currentThread();
        Thread thread = new Thread(() -> {
            log.info("{}开始执行", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}的线程状态为:{}", main.getName(), main.getState());
            log.info("{}执行完毕", Thread.currentThread().getName());
        });

        thread.start();
        log.info("{}开始等待子线程运行完毕", Thread.currentThread().getName());
//        thread.join();
        synchronized (thread) {
            thread.wait();
        }
        log.info("所有子线程执行完毕");
    }
}
