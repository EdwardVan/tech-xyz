package tech.edwardvan.baseconcurrent.core;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.NotRecommend;
import tech.edwardvan.baseconcurrent.annoations.Recommend;

/**
 * 线程的停止
 * <p>
 * 正确的停止方法:A线程使用interrupt来通知B线程,而不是强制停止,而且B线程有线程是否停止的决定权
 * 因为每个线程都有自己的业务逻辑,让线程停止应该是完成了一系列保存工作以后再停止
 *
 * @author EdwardVan
 */
@Slf4j
public class StopExample {
    public static void main(String[] args) throws InterruptedException {
        log.info("主线程");
        //rightWayStopThreadWithoutSleep();
        //rightWayStopThreadWithSleep();
        rightWayStopThreadWithSleepEveryLoop();
    }

    /**
     * run方法内没有sleep或wait方法时,停止线程
     */
    public static void rightWayStopThreadWithoutSleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 4) {
                if (num % 10000 == 0) {
                    log.info("{}是10000的倍数", num);
                }
                num++;
            }
            log.info("任务运行结束了");
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    /**
     * run方法内带有sleep的中断线程的写法
     */
    public static void rightWayStopThreadWithSleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        log.info("{}是100的倍数", num);
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("子线程执行结束");
        });
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    /**
     * 如果在执行过程中,每次循环都会调用sleep或wait等方法,那么不需要每次迭代都检查是否已中断
     *
     * @throws InterruptedException
     */
    public static void rightWayStopThreadWithSleepEveryLoop() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        log.info("{}是100的倍数", num);
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("子线程执行结束");
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
