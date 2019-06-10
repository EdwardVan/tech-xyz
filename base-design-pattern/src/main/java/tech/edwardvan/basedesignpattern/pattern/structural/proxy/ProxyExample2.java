package tech.edwardvan.basedesignpattern.pattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 *
 * @author EdwardVan
 */
public class ProxyExample2 {
    public static void main(String[] args) {
        //动态代理
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();
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

    public static class ProxyHandler implements InvocationHandler {

        private Subject proxied;

        public ProxyHandler(Subject proxied) {
            this.proxied = proxied;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("调用方法之前");

            //转调具体目标对象的方法
            Object invoke = method.invoke(proxied, args);

            System.out.println("调用方法之后");

            return invoke;
        }
    }

    /**
     * 动态代理原理模拟
     */
    public final class $Proxy1 implements Subject {

        private InvocationHandler h;

        private $Proxy1() {
        }

        public $Proxy1(InvocationHandler h) {
            this.h = h;
        }

        @Override
        public void doSomething() {
            try {
                //创建method对象
                Method method = Subject.class.getMethod("doSomething");
                //调用了invoke方法
                h.invoke(this, method, new Object[]{});
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

}
