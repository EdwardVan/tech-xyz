package tech.edwardvan.webspringboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 执行时机:SpringApplication:run()->prepareContext()->applyInitializers()
 * @author EdwardVan
 */
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(TestApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        logger.info("TestApplicationContextInitializer.initialize()");
    }
}
