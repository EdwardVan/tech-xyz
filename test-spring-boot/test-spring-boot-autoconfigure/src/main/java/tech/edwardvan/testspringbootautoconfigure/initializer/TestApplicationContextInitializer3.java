package tech.edwardvan.testspringbootautoconfigure.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.DelegatingApplicationContextInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * 系统初始化器3
 * <p>
 * 通过配置文件添加
 * 配置属性: context.initializer.classes
 * 执行:{@link DelegatingApplicationContextInitializer#initialize(ConfigurableApplicationContext)}
 * <p>
 * 注意:这种方式 @Order 注解会失效
 *
 * @author EdwardVan
 */
@Slf4j
@Order(2)
public class TestApplicationContextInitializer3 implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
