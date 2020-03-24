package tech.edwardvan.basedesignpattern.pattern.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 * <p>
 * 动态代理
 * 动态代理类的源码是在程序运行期间由JVM根据反射等机制动态的生成
 * <p>
 * JDK动态代理原理:
 * 使用Java的反射技术生成动态代理类,只能代理实现了接口的类,没有实现接口的类不能实现动态代理.
 * CGLIB动态代理原理:
 * 运行时动态的生成一个被代理类的子类(通过ASM字节码处理框架实现),子类重写了被代理类中所有非final的方法,在子类中采用方法拦截的技术拦截所有父类方法的调用,不需要被代理类对象实现接口,从而CGLIB动态代理效率比Jdk动态代理反射技术效率要高.
 *
 * @author EdwardVan
 */
@Slf4j
public class ProxyExample2 {
    public static void main(String[] args) {
        Subject subject = new SubjectInvocationHandler(new RealSubject()).getProxy();
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
    private static class RealSubject implements Subject {
        @Override
        public void doSomething() {
            log.info("RealSubject doSomething");
        }
    }

    /**
     * JDK代理处理器
     */
    private static class SubjectInvocationHandler implements InvocationHandler {

        private Subject target;

        public SubjectInvocationHandler(Subject target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("调用方法之前");
            //转调具体目标对象的方法
            Object reuslt = method.invoke(target, args);
            log.info("调用方法之后");
            return reuslt;
        }

        /**
         * 获取代理对象
         */
        public <T> T getProxy() {
            return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        }
    }

    /**
     * JDK动态代理原理模拟
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
