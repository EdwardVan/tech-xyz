package tech.edwardvan.webspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tech.edwardvan.testspringbootautoconfigure.initializer.TestApplicationContextInitializer2;
import tech.edwardvan.testspringbootautoconfigure.listener.TestApplicationListener2;

/**
 * 项目启动入口
 * <p>
 * 项目启动过程
 * 加载RunListener:{@link SpringApplication#getRunListeners(String[])}
 * 参数封装:{@link DefaultApplicationArguments}
 * 准备环境:{@link SpringApplication#prepareEnvironment(org.springframework.boot.SpringApplicationRunListeners, ApplicationArguments)}
 * 输出Banner:{@link SpringApplication#printBanner(ConfigurableEnvironment)}
 * 创建应用上下文:{@link SpringApplication#createApplicationContext()}
 * 准备环境:{@link SpringApplication#prepareContext(ConfigurableApplicationContext, ConfigurableEnvironment, org.springframework.boot.SpringApplicationRunListeners, ApplicationArguments, Banner)}
 * 刷新应用上下文入口:{@link AbstractApplicationContext#refresh()}
 * 获取BeanFactory:{@link AbstractApplicationContext#obtainFreshBeanFactory()}
 * 初始化BeanFactory:{@link AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)}
 * 执行BeanFactoryPostProcessor:{@link AbstractApplicationContext#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory)}
 * 注册BeanPostProcessor:{@link AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)}
 * 初始化MessageSource:{@link AbstractApplicationContext#initMessageSource()}
 * 初始化事件广播器:{@link AbstractApplicationContext#initApplicationEventMulticaster()}
 * 创建web容器:{@link ServletWebServerApplicationContext#createWebServer()}
 * 注册监听器:{@link AbstractApplicationContext#registerListeners()}
 * 创建所有非懒加载的单例类:{@link AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory)}
 * 刷新完成:{@link AbstractApplicationContext#finishRefresh()}
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
