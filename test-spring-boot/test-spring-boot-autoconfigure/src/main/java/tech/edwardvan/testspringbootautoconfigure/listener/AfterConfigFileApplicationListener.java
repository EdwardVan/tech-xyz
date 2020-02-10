package tech.edwardvan.testspringbootautoconfigure.listener;

import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

/**
 * 测试ApplicationListener优先级
 *
 * @see ConfigFileApplicationListener
 * @author EdwardVan
 */
public class AfterConfigFileApplicationListener implements SmartApplicationListener, Ordered {

    @Override
    public int getOrder() {
        // 比 ConfigFileApplicationListener 优先级更低
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent) event;
            Environment environment = preparedEvent.getEnvironment();
            System.out.println("AfterConfigFileApplicationListener -> environment.getProperty(\"server.port\") : " + environment.getProperty("server.port"));
        }
        if (event instanceof ApplicationPreparedEvent) {
        }
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }


}
