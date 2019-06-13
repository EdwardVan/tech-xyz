package tech.edwardvan.basedesignpattern.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * 举例:
 * {@link java.util.EventListener}
 *
 * @author EdwardVan
 */
public class ObserverExample {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserver());
        subject.registerObserver(new ConcreteObserver2());
        subject.notifyObserver("Hello");
    }

    /***
     * 目标接口
     */
    public interface Subject {
        /**
         * 添加观察者
         */
        public void registerObserver(Observer o);

        /**
         * 删除观察者
         */
        public void removeObserver(Observer o);

        /**
         * 通知观察者
         */
        public void notifyObserver(String message);

    }

    /**
     * 具体目标
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
     * 抽象观察者
     */
    public interface Observer {
        /**
         * 当被观察者调用notifyObservers()方法时,观察者的update()方法会被回调
         */
        public void update(String message);
    }

    public static class ConcreteObserver implements Observer {

        @Override
        public void update(String message) {
            System.out.println("ConcreteObserver收到消息:" + message);
        }
    }

    public static class ConcreteObserver2 implements Observer {

        @Override
        public void update(String message) {
            System.out.println("ConcreteObserver2收到消息:" + message);
        }
    }
}
