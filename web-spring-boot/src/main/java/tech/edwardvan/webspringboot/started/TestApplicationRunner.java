package tech.edwardvan.webspringboot.started;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 启动加载器
 * <p>
 * 介绍:Spring容器启动之后执行的一个回调函数
 * 加载&执行:{@link SpringApplication#callRunners(ApplicationContext, ApplicationArguments)}
 *
 * @author EdwardVan
 */
@Component
@Slf4j
public class TestApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
