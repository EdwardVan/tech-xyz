package tech.edwardvan.baseconcurrent.thread;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.NotRecommend;
import tech.edwardvan.baseconcurrent.annoations.Recommend;

/**
 * 线程的异常处理
 *
 * @author EdwardVan
 */
@Slf4j
public class ExceptionExample {
    public static void main(String[] args) throws InterruptedException {
//        cantCatchException();
//        catchInChildThread();
        useUncaughtExceptionHandler();
    }

    /**
     * 子线程的异常不能用传统方法捕获
     */
    static void cantCatchException() {
        try {
            Runnable runnable = () -> {
                throw new RuntimeException();
            };
            new Thread(runnable, "MyThread-1").start();
            Thread.sleep(300);
            new Thread(runnable, "MyThread-2").start();
            Thread.sleep(300);
            new Thread(runnable, "MyThread-3").start();
            Thread.sleep(300);
            new Thread(runnable, "MyThread-4").start();
        } catch (Exception e) {
            log.info("Caught Exception.");
        }
    }

    /**
     * 在子线程中捕获异常
     */
    @NotRecommend
    static void catchInChildThread() throws InterruptedException {

        Runnable runnable = () -> {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                log.info("Caught Exception");
            }
        };
        new Thread(runnable, "MyThread-1").start();
        Thread.sleep(300);
        new Thread(runnable, "MyThread-2").start();
        Thread.sleep(300);
        new Thread(runnable, "MyThread-3").start();
        Thread.sleep(300);
        new Thread(runnable, "MyThread-4").start();
    }

    /**
     * 使用UncaughtExceptionHandler处理子线程报错
     */
    @Recommend
    static void useUncaughtExceptionHandler() throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            log.info("{}线程报错,错误信息:{}", t.getName(), e.getMessage());

        });
        Runnable runnable = () -> {
            throw new RuntimeException();
        };
        new Thread(runnable, "MyThread-1").start();
        Thread.sleep(300);
        new Thread(runnable, "MyThread-2").start();
        Thread.sleep(300);
        new Thread(runnable, "MyThread-3").start();
        Thread.sleep(300);
        new Thread(runnable, "MyThread-4").start();
    }
}
