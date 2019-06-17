package tech.edwardvan.basedesignpattern.pattern.behavioral.memento;

import java.util.Stack;

/**
 * 备忘录模式
 * 优点:
 * 给用户提供了一种可以恢复状态的机制,可以使用户能够比较方便地回到某个历史的状态.
 * 实现了信息的封装,使得用户不需要关心状态的保存细节.
 * 缺点:
 * 消耗资源
 *
 * @author EdwardVan
 */
public class MementoExample {

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("hello");
        Memento memento = originator.saveStateToMemento();
        careTaker.add(memento);
        originator.setState("world");

        System.out.println(originator);
        originator.getStateFromMemento(careTaker.get());
        System.out.println(originator);

    }

    /**
     * 原始对象
     */
    public static class Originator {
        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public Memento saveStateToMemento() {
            return new Memento(state);
        }

        public void getStateFromMemento(Memento memento) {
            state = memento.getState();
        }

        @Override
        public String toString() {
            return "Originator{" +
                    "state='" + state + '\'' +
                    '}';
        }
    }

    /**
     * 快照
     * 无需set方法
     */
    public static class Memento {

        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    /**
     * 快照管理者
     */
    public static class CareTaker {

        private Stack<Memento> mementoStack = new Stack<>();

        public void add(Memento state) {
            mementoStack.push(state);
        }

        public Memento get() {
            return mementoStack.pop();
        }
    }

}
