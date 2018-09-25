package tech.edwardvan.webssmxml.model;

import org.aspectj.lang.ProceedingJoinPoint;

//通知类
public class SpringAdvice {

    public void before(){
        System.out.println("前置通知!");
    }

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知之前!");
        Object proceed = pjp.proceed();//调用目标方法
        System.out.println("环绕通知之后!");
        return proceed;
    }

    public void afterReturning(){
        System.out.println("后置通知(如果有异常则不会调用)!");
    }

    public void after(){
        System.out.println("后置通知!");
    }

    public void afterThrowing(){
        System.out.println("抛出异常后通知!");
    }
}
