package tech.edwardvan.basedesignpattern.pattern.structural.proxy;

/**
 * 代理模式
 * <p>
 * 优点:
 * 代理模式能够协调调用者和被调用者,在一定程度上降低了系统的耦合度.
 * 远程代理使得客户端可以访问在远程机器上的对象,远程机器可能具有更好的计算性能与处理速度,可以快速响应并处理客户端请求.
 * 虚拟代理通过使用一个小对象来代表一个大对象,可以减少系统资源的消耗,对系统进行优化并提高运行速度.
 * 保护代理可以控制对真实对象的使用权限.
 * 缺点:
 * 由于在客户端和真实主题之间增加了代理对象,因此 有些类型的代理模式可能会造成请求的处理速度变慢.
 * 实现代理模式需要额外的工作,有些代理模式的实现 非常复杂.
 * 举例:
 * {@link org.springframework.aop.framework.JdkDynamicAopProxy}
 * {@link org.springframework.aop.framework.CglibAopProxy}
 * {@link org.apache.ibatis.binding.MapperProxy}
 *
 * @author EdwardVan
 */
public class ProxyExample {
    public static void main(String[] args) {
        //静态代理
        Proxy subject = new Proxy(new RealSubject());
        subject.doSomething();
    }

    /**
     * 抽象主题角色
     */
    public interface Subject {
        void doSomething();
    }

    /**
     * 真实主题角色
     */
    public static class RealSubject implements Subject {

        @Override
        public void doSomething() {
            System.out.println("RealSubject doSomething()");
        }
    }

    /**
     * 代理主题角色
     */
    public static class Proxy implements Subject {

        private Subject subject;

        public Proxy(Subject subject) {
            this.subject = subject;
        }

        @Override
        public void doSomething() {
            System.out.println("调用方法之前");
            subject.doSomething();
            System.out.println("调用方法之后");
        }
    }

}
