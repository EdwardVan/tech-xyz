package tech.edwardvan.testspringbootautoconfigure.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;

/**
 * 系统初始化器2
 * <p>
 * 通过创建ApplicationContext时添加
 * 加载:{@link SpringApplicationBuilder#initializers(ApplicationContextInitializer[])}
 *
 * @author EdwardVan
 */
@Slf4j
@Order(1)
public class TestApplicationContextInitializer2 implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
