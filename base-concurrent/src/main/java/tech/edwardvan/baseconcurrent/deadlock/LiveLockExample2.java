package tech.edwardvan.baseconcurrent.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 活锁解决
 * <p>
 * 原因:重试机制不变,消息队列始终重试,吃饭始终谦让
 * 解决:加入随机因素,退避算法
 *
 * @author EdwardVan
 */
@Slf4j
public class LiveLockExample2 {


    public static void main(String[] args) {

        Spoon spoon = new Spoon();

        Diner husband = new Diner("牛郎", spoon, true);
        Diner wife = new Diner("织女", spoon, true);

        spoon.setOwner(husband);

        new Thread(() -> husband.eatWith(wife)).start();

        new Thread(() -> wife.eatWith(husband)).start();
    }

    /**
     * 就餐者
     */
    @AllArgsConstructor
    static class Diner {

        private String name;

        private volatile Spoon spoon;

        private volatile boolean isHungry;

        public void eatWith(Diner partner) {
            while (isHungry) {
                if (spoon.getOwner() != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if (partner.isHungry && random.nextInt(10) < 9) {
                    log.info("{}说:亲爱的,你先吃吧", name);
                    spoon.setOwner(partner);
                    continue;
                }

                spoon.use();
                isHungry = false;
                log.info("{}说:我吃完了", name);
                spoon.setOwner(partner);

            }
        }
    }

    /**
     * 勺子
     */
    @Data
    static class Spoon {

        private Diner owner;

        public synchronized void use() {
            log.info("{}吃完了.", owner.name);
        }
    }


}
