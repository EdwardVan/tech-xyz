package tech.edwardvan.testspringbootautoconfigure.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * 应用事件监听器3
 * <p>
 * 通过配置文件添加
 * 配置属性: context.listener.classes
 * 执行:{@link DelegatingApplicationListener#onApplicationEvent(ApplicationEvent)}
 * <p>
 * 注意:这种方式 @Order 注解会失效
 *
 * @author EdwardVan
 */
@Slf4j
@Order(2)
public class TestApplicationListener3 implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.warn("This is {},event'source is {}", Thread.currentThread().getStackTrace()[1].getMethodName(), event.getSource());
    }
}
