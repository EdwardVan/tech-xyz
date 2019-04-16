package tech.edwardvan.webspringboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 执行时机:SpringApplication:run()->callRunners()
 * @author EdwardVan
 */
@Component
public class TestApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TestApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("TestApplicationRunner.run()");
    }
}
