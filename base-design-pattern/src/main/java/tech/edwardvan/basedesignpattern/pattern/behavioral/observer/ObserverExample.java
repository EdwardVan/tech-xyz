package tech.edwardvan.basedesignpattern.pattern.behavioral.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * <p>
 * 举例:
 * {@link org.springframework.context.ApplicationListener}
 * {@link org.springframework.context.event.ApplicationEventMulticaster}
 *
 * @author EdwardVan
 */
@Slf4j
public class ObserverExample {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserver());
        subject.registerObserver(new ConcreteObserver2());
        subject.notifyObserver("Hello");
    }

    /***
     * 被观察者接口
     */
    public interface Subject {
        /**
         * 添加观察者
         */
        void registerObserver(Observer o);

        /**
         * 删除观察者
         */
        void removeObserver(Observer o);

        /**
         * 通知观察者
         */
        void notifyObserver(String message);

    }

    /**
     * 被观察者实现类
     */
    public static class ConcreteSubject implements Subject {

        private List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObserver(String message) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
    }

    /***
     * 观察者接口
     */
    public interface Observer {
        /**
         * 当被观察者调用notifyObservers()方法时,观察者的update()方法会被回调
         */
        void update(String message);
    }

    /**
     * 观察者接口实现类1
     */
    public static class ConcreteObserver implements Observer {

        @Override
        public void update(String message) {
            log.info("{}收到消息:{}", this.getClass().getSimpleName(), message);
        }
    }

    /**
     * 观察者接口实现类2
     */
    public static class ConcreteObserver2 implements Observer {

        @Override
        public void update(String message) {
            log.info("{}收到消息:{}", this.getClass().getSimpleName(), message);
        }
    }
}
