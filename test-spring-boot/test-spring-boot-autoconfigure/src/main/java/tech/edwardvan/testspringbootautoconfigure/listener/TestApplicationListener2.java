package tech.edwardvan.testspringbootautoconfigure.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;

/**
 * 应用事件监听器2
 * <p>
 * 通过创建ApplicationContext时添加
 * 加载:{@link SpringApplicationBuilder#listeners(ApplicationListener[])}
 *
 * @author EdwardVan
 */
@Slf4j
@Order(1)
public class TestApplicationListener2 implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.warn("This is {},event'source is {}", Thread.currentThread().getStackTrace()[1].getMethodName(), event.getSource());
    }
}
