package tech.edwardvan.webssmannotation.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//切面配置
@Component
@Aspect
public class AspectConfig {

    public static final Logger log = LoggerFactory.getLogger(AspectConfig.class);

    //配置一个切点表达式
    @Pointcut("execution(String tech.edwardvan.webssmannotation.model.StudentTest.toString())")
    public void pointcut(){}

    //在方法调用之前调用通知
    @Before("AspectConfig.pointcut()")
    public void before(){
        log.info("前置通知!");
    }

    //通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为
    @Around("AspectConfig.pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("环绕通知之前!");
        Object proceed = pjp.proceed();//调用目标方法
        log.info("环绕通知之后!");
        return proceed;
    }

    //在方法执行成功之后调用通知
    @AfterReturning("AspectConfig.pointcut()")
    public void afterReturning(){
        log.info("后置通知(如果有异常则不会调用)!");
    }

    //在方法完成之后调用通知，无论方法执行成功与否
    @After("AspectConfig.pointcut()")
    public void after(){
        log.info("后置通知!");
    }

    //在方法抛出异常后进行通知
    @AfterThrowing("AspectConfig.pointcut()")
    public void afterThrowing(){
        log.info("抛出异常后通知!");
    }
}
