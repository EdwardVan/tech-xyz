package tech.edwardvan.baseconcurrent.core;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.NotRecommend;
import tech.edwardvan.baseconcurrent.annoations.Recommend;

/**
 * 线程的创建
 *
 * @author EdwardVan
 */
@Slf4j
public class CreateExample {
    public static void main(String[] args) {
        log.info("主线程");
        runnable();
        thread();
        bothRunnableThread();
    }


    /**
     * 用实现Runnable接口的方式创建线程
     * <p>
     * 优点:
     * 代码解耦
     * 可以利用线程池工具,节约资源
     */
    @Recommend
    public static void runnable() {
        new Thread(() -> log.info("用Runnable方法实现线程")).start();
    }

    /**
     * 用继承Thread类的方式实现线程
     * 缺点:
     * 类不支持双继承,继承了Thread类丧失了拓展性
     */
    @NotRecommend
    public static void thread() {
        new Thread() {
            @Override
            public void run() {
                log.info("用Thread方法实现线程");
            }
        }.start();
    }

    /**
     * 同时使用两种实现方式
     */
    public static void bothRunnableThread() {
        new Thread(() -> log.info("bothRunnableThread中用实现Runnable接口的方式创建线程")) {
            @Override
            public void run() {
                log.info("bothRunnableThread中用继承Thread类的方式实现线程");
            }
        }.start();
    }
}
