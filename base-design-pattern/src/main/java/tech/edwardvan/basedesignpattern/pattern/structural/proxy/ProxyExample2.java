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
        Target proxy = TargetProxy.getProxy(new TargetImpl());
        proxy.execute();
    }

    /**
     * 目标接口
     */
    public interface Target {
        void execute();
    }

    /**
     * 目标接口实现
     */
    private static class TargetImpl implements Target {
        @Override
        public void execute() {
            log.info("HelloWord");
        }
    }

    /**
     * 代理类
     */
    private static class TargetProxy implements InvocationHandler {

        private Target target;

        public TargetProxy(Target target) {
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
        public static Target getProxy(Target target) {
            return (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TargetProxy(target));
        }
    }

    /**
     * JDK动态代理原理模拟
     */
    public final class $Proxy1 implements Target {

        private InvocationHandler h;

        private $Proxy1() {
        }

        public $Proxy1(InvocationHandler h) {
            this.h = h;
        }

        @Override
        public void execute() {
            try {
                //创建method对象
                Method method = Target.class.getMethod(Thread.currentThread().getStackTrace()[1].getMethodName());
                //调用了invoke方法
                h.invoke(this, method, new Object[]{});
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

}
