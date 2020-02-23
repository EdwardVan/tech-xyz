package tech.edwardvan.baseconcurrent.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产消费者模式
 * 用wait/notify来实现生产者消费者模式
 *
 * @author EdwardVan
 */
@Slf4j
public class ProducerConsumerModelExample {

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    /**
     * 生产者
     */
    static class Producer implements Runnable {

        EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    storage.put(new Object());
                } catch (InterruptedException e) {
                    //恢复中断
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable {

        EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    storage.take();
                } catch (InterruptedException e) {
                    //恢复中断
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 仓库
     */
    static class EventStorage {
        /**
         * 最大数量
         */
        private int maxSize;
        private List<Object> storage;

        public EventStorage() {
            this.maxSize = 10;
            storage = new ArrayList();
        }

        /**
         * 向仓库添加产品
         */
        public synchronized void put(Object o) throws InterruptedException {
            //如果仓库满了
            if (storage.size() == maxSize) {
                log.info("仓库满了,{}进入WAITING状态", Thread.currentThread().getName());
                this.wait();
                log.info("{}被唤醒", Thread.currentThread().getName());
            }
            storage.add(o);
            log.info("{}成功向仓库添加一个产品,目前数量为{}", Thread.currentThread().getName(), storage.size());
            this.notifyAll();
        }

        /**
         * 从仓库取出产品
         */
        public synchronized Object take() throws InterruptedException {
            //如果仓库空了
            if (storage.size() == 0) {
                log.info("仓库目前数量为0,{}进入WAITING状态", Thread.currentThread().getName());
                this.wait();
                log.info("{}被唤醒", Thread.currentThread().getName());
            }
            Object remove = storage.remove(0);
            log.info("{}成功从仓库取出产品,目前数量为{}", Thread.currentThread().getName(), storage.size());
            this.notifyAll();
            return remove;
        }
    }
}




