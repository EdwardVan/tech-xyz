package tech.edwardvan.baseconcurrent.jmm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JMM(Java Memory Model)
 * <p>
 * 1. 所有的变量都存储在主内存中,同时每个线程也有自己独立的工作内存,工作内存中的变量内容是主内存中的拷贝.
 * 2. 线程不能直接读写主内存中的变量,而是只能操作自己工作内存中的变量,然后再同步到主内存中.
 * 3. 主内存是多个线程共享的,但线程间不共享工作内存,如果线程间需要通信,必须借助主内存中转来完成.
 * <p>
 * 重排序
 * 在线程内部的两行代码的实际执行顺序和代码在Java文件中的顺序不一致,代码指令并不是严格按照代码语句顺序执行的,它们的顺序被改变了.
 * 1. 编译器优化:包括VM,JIT编译器等
 * 2. CPU指令重排:就算编译器不发生重排,CPU也可能对指令进行重排
 * 3. 内存的"重排序":线程A的修改线程B却看不到,引出可见性问题
 * <p>
 * as-if-serial
 * 不管怎么重排序,单线程程序的执行结果不能改变.编译器、runtime和处理器都必须遵守as-if-serial语义.
 * 为了遵守该语义,编译器和处理器不会对存在数据依赖关系的操作做重排序.
 *
 * @author EdwardVan
 */
@Slf4j
public class JMMExample {

    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread thread1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread thread2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            //符合线程启动规则
            thread1.start();
            thread2.start();

            //符合线程终止规则
            thread1.join();
            thread2.join();

            log.info("第{}次,x的值为:{},y的值为:{}", i, x, y);
            if (x == 1 && y == 1) {
                break;
            }
        }
    }
}
