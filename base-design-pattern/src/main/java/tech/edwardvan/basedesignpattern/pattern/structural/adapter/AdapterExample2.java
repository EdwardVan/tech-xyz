package tech.edwardvan.basedesignpattern.pattern.structural.adapter;

/**
 * 适配器模式
 * <p>
 * 对象适配器(强调组合)
 *
 * @author EdwardVan
 */
public class AdapterExample2 {

    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();
        target = new Adapter();
        target.request();
    }

    /**
     * 被适配者
     */
    public static class Adaptee {
        public void adapteeRequest() {
            System.out.println("被适配者的方法");
        }
    }

    /**
     * 目标接口
     */
    public interface Target {
        void request();
    }

    /**
     * 目标实现类
     */
    public static class ConcreteTarget implements Target {
        @Override
        public void request() {
            System.out.println("concreteTarget目标方法");
        }
    }

    /**
     * 适配器
     */
    public static class Adapter implements Target {

        Adaptee adaptee = new Adaptee();

        @Override
        public void request() {
            System.out.println("do something");
            adaptee.adapteeRequest();
            System.out.println("do something");
        }
    }

}
