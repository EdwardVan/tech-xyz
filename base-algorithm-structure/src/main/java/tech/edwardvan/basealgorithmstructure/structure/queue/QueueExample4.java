package tech.edwardvan.basealgorithmstructure.structure.queue;

import java.util.Random;

/**
 * 比较不同队列的效率
 */
public class QueueExample4 {
    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        QueueExample.MyArrayQueue<Integer> arrayQueue = new QueueExample.MyArrayQueue<>();
        System.out.println("MyArrayQueue, time: " + testQueue(arrayQueue, opCount) + " s");

        QueueExample2.MyLoopQueue<Integer> loopQueue = new QueueExample2.MyLoopQueue<>();
        System.out.println("MyLoopQueue, time: " + testQueue(loopQueue, opCount) + " s");

        QueueExample3.MyListQueue<Integer> listQueue = new QueueExample3.MyListQueue<>();
        System.out.println("MyLoopQueue, time: " + testQueue(listQueue, opCount) + " s");
    }

}
