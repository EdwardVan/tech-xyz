package tech.edwardvan.baseconcurrent.jmm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 先行发生原则(happens-before)
 * <p>
 * 如果一个操作的执行结果需要对另一个操作可见,那么这两个操作之间必须要存在happens-before关系,而且第一个操作的执行顺序排在第二个操作之前,这个的两个操作既可以在同一个线程,也可以在不同的两个线程中
 * 两个操作之间存在happens-before关系,并不意味着一定要按照happens-before原则制定的顺序来执行.如果重排序之后的执行结果与按照happens-before关系来执行的结果一致,那么这种重排序并不非法.
 * <p>
 * 1.程序次序规则(Program Order Rule):在一个线程内,一段代码的执行结果是有序的.依然会有指令重排,但是不论怎么重排序,结果都是按照代码顺序生成的不会变
 * 2.管程锁定规则(Monitor Lock Rule):对于同一个锁来说,一个线程对这个锁解锁之后,另一个线程获取了这个锁,则它能看到前一个线程的操作结果
 * 3.volatile变量规则(Volatile Variable Rule):如果一个线程先去写一个volatile变量,然后一个线程去读这个变量,那么这个写操作的结果一定对读的这个线程可见
 * 4.线程启动规则(Thread Start Rule):在主线程A执行过程中,启动子线程B,那么线程A在启动子线程B之前对共享变量的修改结果对线程B可见
 * 5.线程终止规则(Thread Termination Rule):在主线程A执行过程中,子线程B终止,那么线程B在终止之前对共享变量的修改结果在线程A中可见
 * 6.线程中断规则(Thread Interruption Rule):对线程interrupt()方法的调用先行发生于被中断线程代码检测到中断事件的发生
 * 7.对象终结规则(Finalizer Rule):一个对象的初始化的完成,也就是构造函数执行的结束一定 happens-before它的finalize()方法
 * 8.传递性(Transitivity):如果操作A先行发生于操作B,操作B先行发生于操作C,那就可以得出操作A先行发生于操作C的结论
 *
 * @author EdwardVan
 */
@Slf4j
public class HappensBeforeExample {

    private static int a = 0;
    private static int b = 0;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            a = 0;
            b = 0;

            Thread thread1 = new Thread(() -> {
                a = 1;//1
                b = a;//2
            });

            Thread thread2 = new Thread(() -> {
                log.info("b:{}", b);//3
                log.info("a:{}", a);//4
                log.info("---------");
            });

            //符合线程启动规则
            thread1.start();
            thread2.start();

            //符合线程终止规则
            thread1.join();
            thread2.join();
        }
    }
}
