package tech.edwardvan.testspringbootautoconfigure.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;

/**
 * 系统初始化器
 * <p>
 * 通过 spring.factories 配置方式添加
 * <p>
 * 介绍:Spring容器刷新之前执行的一个回调函数
 * 作用:向SpringBoot容器中注册属性
 * 加载:{@link SpringApplication#SpringApplication(ResourceLoader, Class[])}
 * 执行:{@link SpringApplication#applyInitializers(ConfigurableApplicationContext)}
 *
 * @author EdwardVan
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
