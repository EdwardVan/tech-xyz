package tech.edwardvan.mallmono.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Service层Log切面
 * 1. 前置通知：在方法调用之前执行
 * 2. 后置通知：在方法正常调用之后执行
 * 3. 环绕通知：在方法调用之前和之后，都分别可以执行的通知
 * 4. 异常通知：如果在方法调用过程中发生异常，则通知
 * 5. 最终通知：在方法调用之后执行
 *
 * @author EdwardVan
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    /**
     * 记录方法执行时间
     * <p>
     * 切面表达式：
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     */
    @Around("execution(* tech.edwardvan.mallmono.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long takeTime = endTime - startTime;
        String logStr = joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + "方法执行耗时:" + takeTime + "毫秒";
        if (takeTime > 3000) {
            log.error(logStr);
        } else if (takeTime > 2000) {
            log.warn(logStr);
        } else {
            log.info(logStr);
        }
        return result;
    }

}
