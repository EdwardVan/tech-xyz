package tech.edwardvan.basedesignpattern.pattern.behavioral.mediator;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式
 * <p>
 * 优点:
 * 简化了对象之间的交互
 * 将各同事解耦.
 * 减少子类生成.
 * 缺点:
 * 可能会导致具体中介者类非常复杂,使得系统难以维护.
 *
 * @author EdwardVan
 */
@Slf4j
public class MediatorExample {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague colleague = new ConcreteColleague(mediator);
        Colleague colleague2 = new ConcreteColleague2(mediator);
        colleague.send("hello, I am colleague1");
        colleague2.send("hello, I am colleague2");
    }

    /**
     * 抽象中介者
     */
    public static abstract class Mediator {

        protected List<Colleague> colleagues = new ArrayList<>();

        /**
         * 注册
         */
        public void register(Colleague c) {
            colleagues.add(c);
        }

        /**
         * 移除
         */
        public void remove(Colleague c) {
            colleagues.remove(c);
        }

        /**
         * 操作
         */
        public abstract void operation(String message);
    }

    /**
     * 具体中介者
     */
    public static class ConcreteMediator extends Mediator {

        @Override
        public void operation(String message) {
            for (Colleague colleague : colleagues) {
                colleague.receive(message);
            }
        }
    }

    /**
     * 抽象同事类
     */
    public static abstract class Colleague {
        protected Mediator mediator;

        public Colleague(Mediator mediator) {
            this.mediator = mediator;
            mediator.register(this);
        }

        /**
         * 发送消息
         */
        public abstract void send(String message);

        /**
         * 接受消息
         */
        public abstract void receive(String message);

    }

    /**
     * 具体同事类
     */
    public static class ConcreteColleague extends Colleague {

        public ConcreteColleague(Mediator mediator) {
            super(mediator);
        }

        @Override
        public void send(String message) {
            log.info("ConcreteColleague发送消息:{}", message);
            mediator.operation(message);
        }

        @Override
        public void receive(String message) {
            log.info("ConcreteColleague收到消息:{}", message);
        }
    }

    /**
     * 具体同事类2
     */
    public static class ConcreteColleague2 extends Colleague {

        public ConcreteColleague2(Mediator mediator) {
            super(mediator);
        }

        @Override
        public void send(String message) {
            log.info("ConcreteColleague2发送消息:{}", message);
            mediator.operation(message);
        }

        @Override
        public void receive(String message) {
            log.info("ConcreteColleague2收到消息:{}", message);
        }
    }
}
