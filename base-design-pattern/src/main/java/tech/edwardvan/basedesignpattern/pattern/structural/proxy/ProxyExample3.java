package tech.edwardvan.basedesignpattern.pattern.structural.proxy;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 代理模式
 * <p>
 * 动态代理-Cglib
 *
 * @author EdwardVan
 */
@Slf4j
public class ProxyExample3 {
    public static void main(String[] args) {
        RealSubject realSubject = CglibProxyFactory.getProxy(RealSubject.class);
        realSubject.doSomething();
        realSubject.eatSomething();
    }

    /**
     * 真实主题角色
     */
    @NoArgsConstructor
    private static class RealSubject {
        public void doSomething() {
            log.info("RealSubject doSomething");
        }

        public void eatSomething() {
            log.info("RealSubject eatSomething");
        }
    }

    /**
     * Cglib拦截器
     */
    private static class CglibProxyFactory implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            log.info("调用{}方法之前", method.getName());
            //转调具体目标对象的方法
            Object reuslt = methodProxy.invokeSuper(o, objects);
            log.info("调用{}方法之后", method.getName());
            return reuslt;
        }

        /**
         * 获取代理对象
         */
        public static <T> T getProxy(Class<T> clazz) {
            //用于生成代理对象
            Enhancer enhancer = new Enhancer();
            //设置目标类为代理对象的父类
            enhancer.setSuperclass(clazz);
            //设置回调对象为本身
            enhancer.setCallback(new CglibProxyFactory());
            //生成一个代理类对象
            return (T) enhancer.create();
        }

    }

}
