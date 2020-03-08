package tech.edwardvan.webspringboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 执行时机为容器启动完成的时候
 * 原理:{@link SpringApplication#callRunners(ApplicationContext, ApplicationArguments)}
 *
 * @author EdwardVan
 */
@Component
@Slf4j
public class TestApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("TestApplicationRunner.run()");
    }
}
