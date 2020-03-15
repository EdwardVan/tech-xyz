package tech.edwardvan.webspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tech.edwardvan.testspringbootautoconfigure.initializer.TestApplicationContextInitializer2;
import tech.edwardvan.testspringbootautoconfigure.listener.TestApplicationListener2;

/**
 * 项目启动入口
 *
 * EnableXXX注解模块举例
 *      框架实现               @Enable 注解模块                         激活模块
 *      Spring Framework    {@link EnableWebMvc}                    Web MVC 模块
 *                          {@link EnableTransactionManagement}     事务管理模块
 *                          {@link EnableCaching}                   Caching 模块
 *                          {@link EnableMBeanExport}               JMX 模块
 *                          {@link EnableAsync}                     异步处理模块
 *                          @EnableWebFlux                          Web Flux 模块
 *                          {@link EnableAspectJAutoProxy}          AspectJ 代理模块
 *
 *      Spring Boot         {@link EnableAutoConfiguration}         自动装配模块
 *                          @EnableManagementContext                Actuator 管理模块
 *                          {@link EnableConfigurationProperties}   配置属性绑定模块
 *                          @EnableOAuth2Sso                        OAuth2 单点登录模块
 *
 * {@link WebMvcAutoConfiguration}:WebMvc自动配置
 * {@link WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter}:WebMvc扩展配置类
 * {@link ErrorMvcAutoConfiguration}:错误处理机制
 * {@link DispatcherServletAutoConfiguration}:前端控制器配置
 * {@link ServletWebServerFactoryAutoConfiguration}:嵌入式Servlet容器自动配置
 *
 * @author EdwardVan
 */
@SpringBootApplication
@MapperScan("tech.edwardvan.webspringboot.dao")
/**
 * 开启异步任务
 */
@EnableAsync
/**
 * 开启定时任务
 */
@EnableScheduling
/**
 * 使用缓存步骤
 *      1.导入spring-boot-starter-cache模块
 *      2.@EnableCaching开启缓存
 *      3.使用缓存注解
 */
@EnableCaching
public class WebSpringBootApplication {

    public static void main(String[] args) {
        // 启动方式一
        //SpringApplication.run(WebSpringBootApplication.class, args);
        // 启动方式二
        ConfigurableApplicationContext run = new SpringApplicationBuilder(WebSpringBootApplication.class)
                .initializers(new TestApplicationContextInitializer2())
                .listeners(new TestApplicationListener2())
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
