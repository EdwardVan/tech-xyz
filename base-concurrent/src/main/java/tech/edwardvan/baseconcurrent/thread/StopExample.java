package tech.edwardvan.baseconcurrent.thread;

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
        //rightWayStopThreadWithSleepEveryLoop();
        //cantInterrupt();
        //rightWayStopThreadInProd();
        rightWayStopThreadInProd2();
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

    /**
     * 如果while里面放try/catch,会导致中断失效
     * 一个线程在运行状态中,其中断标志被设置为true之后,一旦线程调用了wait,join,sleep方法中的一种,
     * 立马抛出个InterruptedException,且中断标志被程序会自动清除,重新设置为false
     */
    @NotRecommend
    public static void cantInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    log.info("{}是100的倍数", num);
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("子线程执行结束");
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    /**
     * 最佳实践:传递中断
     * 在子方法签名中抛出异常,然后在顶层方法run()中try/catch异常进行处理
     */
    @Recommend
    public static void rightWayStopThreadInProd() throws InterruptedException {

        Thread thread = new Thread(() -> {
            log.info("子线程执行开始");
            while (true && !Thread.currentThread().isInterrupted()) {
                log.info("go");
                try {
                    throwInMethod();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    //保存日志、停止程序
                    log.info("保存日志、停止程序等操作");
                    e.printStackTrace();
                }
            }
            log.info("子线程执行结束");

        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    /**
     * 不要再子方法中try/catch InterruptedException异常
     */
    private static void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     * 最佳实践2:恢复中断
     * 子方法捕获异常但是恢复中断状态,run方法中检查中断状态
     */
    @Recommend
    public static void rightWayStopThreadInProd2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.info("子线程执行开始");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("Interrupted,程序运行结束");
                    break;
                }
                reInterrupt();
            }
            log.info("子线程执行结束");

        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    /**
     * 如果一定要在子方法中捕获异常,则在catch语句中调用Thread.currentThread().interrupt()来恢复设置中断状态
     */
    private static void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //恢复中断
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


}
