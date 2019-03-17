package tech.edwardvan.webssmannotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 *
 * @author EdwardVan
 */
@Component
public class ApplicationListenerConfig implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationListenerConfig.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.debug("收到事件:{}", event);
    }
}
