package tech.edwardvan.webssmxml.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring事务通知类
 *
 * @author EdwardVan
 */
public class SpringAdvice {

    private static final Logger logger = LoggerFactory.getLogger(SpringAdvice.class);

    public void before() {
        logger.info("前置通知!");
    }

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("环绕通知之前!");
        //调用目标方法
        Object proceed = pjp.proceed();
        logger.info("环绕通知之后!");
        return proceed;
    }

    public void afterReturning() {
        logger.info("后置通知(如果有异常则不会调用)!");
    }

    public void after() {
        logger.info("后置通知!");
    }

    public void afterThrowing() {
        logger.info("抛出异常后通知!");
    }
}
