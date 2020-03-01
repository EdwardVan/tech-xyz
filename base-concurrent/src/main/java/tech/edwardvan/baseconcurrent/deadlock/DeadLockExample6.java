package tech.edwardvan.baseconcurrent.deadlock;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 解决死锁-破坏循环等待条件
 *
 * @author EdwardVan
 */
@Slf4j
public class DeadLockExample6 {

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[philosophers.length];
        //筷子初始化
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        //哲学家初始化
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }

    /**
     * 哲学家
     */
    @AllArgsConstructor
    public static class Philosopher implements Runnable {
        /**
         * 左边的筷子
         */
        private Object leftChopstick;
        /**
         * 右边的筷子
         */
        private Object rightChopstick;

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("思考中...");
                    synchronized (rightChopstick) {
                        doAction("已经拿起左边的筷子");
                        synchronized (leftChopstick) {
                            doAction("已经拿起右边的筷子,然后吃饭");
                            doAction("已经放下右边的筷子");
                        }
                        doAction("已经放下左边的筷子");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            log.info("{}正在{}", Thread.currentThread().getName(), action);
            //随机休眠模拟行为消耗的时间
            Thread.sleep((long) (Math.random() * 10));
        }
    }
}
