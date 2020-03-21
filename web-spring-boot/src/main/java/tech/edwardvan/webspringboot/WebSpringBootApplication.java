package tech.edwardvan.webspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tech.edwardvan.testspringbootautoconfigure.initializer.TestApplicationContextInitializer2;
import tech.edwardvan.testspringbootautoconfigure.listener.TestApplicationListener2;

/**
 * 项目启动入口
 * <p>
 * EnableXXX注解模块举例
 * {@link EnableWebMvc}                    Web MVC 模块
 * {@link EnableTransactionManagement}     事务管理模块
 * {@link EnableCaching}                   Caching 模块
 * {@link EnableMBeanExport}               JMX 模块
 * {@link EnableAsync}                     异步处理模块
 * {@link EnableAspectJAutoProxy}          AspectJ 代理模块
 * {@link EnableAutoConfiguration}         自动装配模块
 * {@link EnableConfigurationProperties}   配置属性绑定模块
 *
 * @author EdwardVan
 */
@SpringBootApplication
@MapperScan("tech.edwardvan.webspringboot.dao")
@EnableAsync
@EnableScheduling
@EnableCaching
@PropertySource({"prioritytest.properties"})
public class WebSpringBootApplication {

    public static void main(String[] args) {
        // 启动方式一
        //SpringApplication.run(WebSpringBootApplication.class, args);
        // 启动方式二
        ConfigurableApplicationContext run = new SpringApplicationBuilder(WebSpringBootApplication.class)
                .initializers(new TestApplicationContextInitializer2())
                .listeners(new TestApplicationListener2())
                .web(WebApplicationType.SERVLET)
                .properties("properties.priority.test:prioritytest-1")
                .run(args);
    }

}
