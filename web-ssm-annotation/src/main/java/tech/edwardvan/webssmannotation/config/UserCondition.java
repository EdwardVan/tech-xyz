package tech.edwardvan.webssmannotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * spring条件化配置
 *
 * @author EdwardVan
 */
public class UserCondition implements Condition {

    private static final Logger logger = LoggerFactory.getLogger(UserCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // BeanDefinition实例的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取运行时环境变量
        Environment environment = context.getEnvironment();

        logger.debug("UserCondition.matches() 返回 true");

        return true;
    }
}
