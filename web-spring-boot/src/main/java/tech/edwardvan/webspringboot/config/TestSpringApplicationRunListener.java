package tech.edwardvan.webspringboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 执行时机:SpringApplication->run()->listeners.starting()等
 * @author EdwardVan
 */
public class TestSpringApplicationRunListener implements SpringApplicationRunListener {

    private static final Logger logger = LoggerFactory.getLogger(TestSpringApplicationRunListener.class);

    public TestSpringApplicationRunListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
        logger.info("TestSpringApplicationRunListener.starting()");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        logger.info("TestSpringApplicationRunListener.environmentPrepared()");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        logger.info("TestSpringApplicationRunListener.contextPrepared()");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        logger.info("TestSpringApplicationRunListener.contextLoaded()");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        logger.info("TestSpringApplicationRunListener.started()");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        logger.info("TestSpringApplicationRunListener.running()");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        logger.info("TestSpringApplicationRunListener.failed()");
    }
}
