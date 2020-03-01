package tech.edwardvan.baseconcurrent.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 活锁示例
 * 虽然线程并没有阻塞,也始终在运行,但是程序却得不到进展,因为线程始终重复做同样的事
 * <p>
 * 工程中的活锁实例:
 * 消息队列中,消息如果处理失败,就放在队列开头重试,由于依赖服务出了问题,处理该消息一直失败,没阻塞,但程序无法继续
 * 解决:放到队列尾部、重试限制(次数过多放入数据库中)
 *
 * @author EdwardVan
 */
@Slf4j
public class LiveLockExample {


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
                if (partner.isHungry) {
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
