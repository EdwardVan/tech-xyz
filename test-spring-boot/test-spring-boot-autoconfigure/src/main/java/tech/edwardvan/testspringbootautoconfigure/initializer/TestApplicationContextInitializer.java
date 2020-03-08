package tech.edwardvan.testspringbootautoconfigure.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 用于在spring容器刷新之前初始化ApplicationContext的回调接口
 * <p>
 * 加载:{@link SpringApplication#getSpringFactoriesInstances(Class)}
 * 执行:{@link SpringApplication#applyInitializers(ConfigurableApplicationContext)}
 *
 * @author EdwardVan
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("applicationContext'id is {}", applicationContext.getId());
        log.info("This is TestApplicationContextInitializer.initialize()");
    }
}
