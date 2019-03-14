package tech.edwardvan.webssmannotation.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切面配置
 *
 * @author EdwardVan
 *
 * spring aop原理
 *      创建代理对象核心代码: wrapIfNecessary(bean, beanName, cacheKey)
 *      1. 获取增强器(通知方法)  Object[] specificInterceptors
 *          1) 找到候选的所有的增强器
 *          2) 筛选出能在bean使用的增强器
 *          3) 给增强器排序
 *      2. 保存当前bean到advisedBeans中
 *      3. 如果当前bean需要增强,创建当前bean的代理对象
 *          1) 获取对应的增强器
 *          2) 保存到proxyFactory
 *          3) 创建代理对象(JdkDynamicAopProxy或者ObjenesisCglibAopProxy)
 *      4. 给容器中返回增强了的代理对象
 *      5. 以后容器中获取到的就是这个组件的代理对象,执行目标方法的时候,代理对象就会执行通知方法的流程
 *
 *      执行目标方法
 *      1. 代理对象执行目标方法
 *      2. CglibAopProxy.intercept()
 *          1) 得到目标方法的拦截器链(增强器包装成拦截器MethodInterceptor)
 *          2) 利用拦截器的链式机制,依次进入每一个拦截器进行执行
 *      3. 效果
 *          正常执行:前置通知-》目标方法-》后置通知-》返回通知
 *          出现异常:前置通知-》目标方法-》后置通知-》异常通知
 *
 */
@Component
@Aspect
public class AspectConfig {

    public static final Logger log = LoggerFactory.getLogger(AspectConfig.class);

    /**
     * 配置一个切点表达式
     */
    @Pointcut("execution(String tech.edwardvan.webssmannotation.model.Student.toString())")
    public void pointcut() {
    }

    /**
     * 在方法调用之前调用通知
     */
    @Before("AspectConfig.pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("方法{}的前置通知", joinPoint.getSignature().getName());
    }

    /**
     * 通知包裹了被通知的方法,在被通知的方法调用之前和调用之后执行自定义的行为
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("AspectConfig.pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("环绕通知之前");
        // 调用目标方法
        Object proceed = pjp.proceed();
        log.info("环绕通知之后");
        return proceed;
    }

    /**
     * 在方法执行成功之后调用通知
     */
    @AfterReturning(value = "AspectConfig.pointcut()", returning = "result")
    public void afterReturning(Object result) {
        log.info("后置通知(如果有异常则不会调用),返回值:{}", result.toString());
    }

    /**
     * 在方法完成之后调用通知,无论方法执行成功与否
     */
    @After("AspectConfig.pointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("方法{}的后置通知", joinPoint.getSignature().getName());
    }

    /**
     * 在方法抛出异常后进行通知
     */
    @AfterThrowing(value = "AspectConfig.pointcut()", throwing = "exception")
    public void afterThrowing(Exception exception) {
        log.info("抛出异常后通知,异常:{}", exception.toString());
    }
}
