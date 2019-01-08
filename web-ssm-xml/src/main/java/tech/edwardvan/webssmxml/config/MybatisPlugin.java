package tech.edwardvan.webssmxml.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * Mybatis插件
 *
 * @author EdwardVan
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
public class MybatisPlugin implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MybatisPlugin.class);

    /**
     * 拦截
     * 拦截目标对象的目标方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("Mybatis插件拦截方法:" + invocation.getMethod().getName());
        //执行目标方法
        Object proceed = invocation.proceed();
        return proceed;
    }

    /**
     * 包装目标对象
     * 为目标对象创建代理对象
     */
    @Override
    public Object plugin(Object target) {
        logger.info("Mybatis插件将要包装的对象:" + target.getClass().getName());
        //可以借助Plugin类创建代理对象
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    /**
     * 插件注册时的配置
     */
    @Override
    public void setProperties(Properties properties) {
        logger.info("Mybatis插件注册时配置:" + properties.toString());
    }
}
