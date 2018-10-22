package tech.edwardvan.baseconcurrent.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier示例
 */
public class CyclicBarrierExample2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            final int threadNum = i;
            executor.execute(() -> {
                try {
                    if (threadNum == 4) {
                        Thread.sleep(5000);
                    }
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) {
        System.out.println(threadNum + " is ready");
        try {
            barrier.await(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        System.out.println(threadNum + " continue");
    }
}
