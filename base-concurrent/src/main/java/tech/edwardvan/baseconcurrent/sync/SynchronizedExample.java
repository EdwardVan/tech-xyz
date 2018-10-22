package tech.edwardvan.baseconcurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {

    // 修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("test1 {" + j + "} - {" + i + "}");
            }
        }
    }

    // 修饰一个成员方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            System.out.println("test2 {" + j + "} - {" + i + "}");
        }
    }

    public static void main(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example1.test2(1));
        executorService.execute(() -> example2.test2(2));
        executorService.shutdown();
    }
}
