package tech.edwardvan.testspringbootautoconfigure.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;

/**
 * 应用事件监听器
 * <p>
 * 通过 spring.factories 配置方式添加
 * <p>
 * 加载:{@link SpringApplication#SpringApplication(ResourceLoader, Class[])}
 * 执行:{@link EventPublishingRunListener} to publish {@link SpringApplicationEvent}s
 *
 * @author EdwardVan
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.warn("This is {},event'source is {}", Thread.currentThread().getStackTrace()[1].getMethodName(), event.getSource());
    }
}
