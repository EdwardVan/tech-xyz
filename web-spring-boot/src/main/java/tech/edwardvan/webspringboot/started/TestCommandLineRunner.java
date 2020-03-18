package tech.edwardvan.webspringboot.started;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
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
public class TestCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
