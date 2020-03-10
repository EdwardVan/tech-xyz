package tech.edwardvan.baseconcurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder示例
 * <p>
 * 原理(空间换时间):
 * LongAdder引入了分段累加的概念,内部有一个base变量和一个Cell[]数组共同参与计数
 * base变量:竞争不激烈,直接累加到该变量上
 * Cell[]数组:竞争激烈,各个线程分散累加到自己的槽Cell[i]中
 * <p>
 * 在低争用下,AtomicLong和LongAdder这两个类具有相似的特征.但是在竞争激烈的情况下,LongAdder的预期吞吐量要高得多,但要消耗更多的空间
 * LongAdder适合的场景是统计求和计数的场景,而且LongAdder基本只提供了add方法,而AtomicLong还具有cas方法
 *
 * @author EdwardVan
 */
@ThreadSafe
@Slf4j
public class LongAdderExample {

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.increment();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        long endTime = System.currentTimeMillis();
        log.info("count:{},消耗时长:{}", count.sum(), endTime - startTime);
    }
}
