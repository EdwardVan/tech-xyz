package tech.edwardvan.testspringbootautoconfigure.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 应用事件监听器
 * <p>
 * 加载:{@link SpringApplication#getSpringFactoriesInstances(Class)}
 * 执行:{@link EventPublishingRunListener} to publish {@link SpringApplicationEvent}s
 *
 * @author EdwardVan
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("This is TestApplicationListener.onApplicationEvent(), event'source is {}", event.getSource());
    }
}
