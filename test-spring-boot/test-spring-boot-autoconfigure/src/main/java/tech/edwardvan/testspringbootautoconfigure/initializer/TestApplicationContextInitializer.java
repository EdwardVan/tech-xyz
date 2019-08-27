package tech.edwardvan.testspringbootautoconfigure.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 执行时机:SpringApplication:run()->prepareContext()->applyInitializers()
 *
 * @author EdwardVan
 */
@Slf4j
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("TestApplicationContextInitializer.initialize()");
    }
}
