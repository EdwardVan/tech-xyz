package tech.edwardvan.basedesignpattern.pattern.structural.adapter;

/**
 * 适配器模式
 * 优点:
 * 将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，而无须修改原有代码。
 * 增加了类的透明性和复用性，将具体的实现封装在适配者类中，对于客户端类来说是透明的，而且提高了适配者的复用性。
 * 缺点:
 * 增加系统复杂性。
 * 举例:
 * {@link org.springframework.aop.framework.adapter.AdvisorAdapter}
 * {@link org.springframework.web.servlet.HandlerAdapter}
 * <p>
 * 类适配器(强调继承)
 *
 * @author EdwardVan
 */
public class AdapterExample {
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
    public static class Adapter extends Adaptee implements Target {
        @Override
        public void request() {
            System.out.println("do something");
            super.adapteeRequest();
            System.out.println("do something");
        }
    }
}
