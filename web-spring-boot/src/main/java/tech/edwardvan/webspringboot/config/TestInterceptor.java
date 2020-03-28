package tech.edwardvan.webspringboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import org.springframework.stereotype.Component;

import java.sql.Statement;

/**
 * Mybatis插件
 *
 * @author EdwardVan
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
@Component
@Slf4j
public class TestInterceptor implements Interceptor {


    /**
     * 拦截
     * 拦截目标对象的目标方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("Mybatis插件拦截方法:{}", invocation.getMethod().getName());
        //执行目标方法
        Object proceed = invocation.proceed();
        return proceed;
    }
}
