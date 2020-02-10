package tech.edwardvan.webspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tech.edwardvan.testspringbootautoconfigure.model.Test;

/**
 * 项目启动入口
 *
 * EnableXXX注解模块举例
 *      框架实现               @Enable 注解模块                  激活模块
 *      Spring Framework    @EnableWebMvc                    Web MVC 模块
 *                          @EnableTransactionManagement     事务管理模块
 *                          @EnableCaching                   Caching 模块
 *                          @EnableMBeanExport               JMX 模块
 *                          @EnableAsync                     异步处理模块
 *                          @EnableWebFlux                   Web Flux 模块
 *                          @EnableAspectJAutoProxy          AspectJ 代理模块
 *
 *      Spring Boot         @EnableAutoConfiguration         自动装配模块
 *                          @EnableManagementContext         Actuator 管理模块
 *                          @EnableConfigurationProperties   配置属性绑定模块
 *                          @EnableOAuth2Sso                 OAuth2 单点登录模块
 *
 *
 *
 * WebMvc自动配置配置(WebMvcAutoConfigurationAdapter):
 *      静态资源文件夹映射->addResourceHandlers
 *      配置欢迎页映射->welcomePageHandlerMapping
 *      配置喜欢的图标->FaviconConfiguration
 *      自动配置视图解析器->viewResolver
 *      自动配置转换器和格式化器->addFormatters
 *
 * 错误处理机制(ErrorMvcAutoConfiguration):
 *      ErrorPageCustomizer:配置错误页面,系统出现错误以后跳转到/error请求进行处理
 *      basicErrorController:处理默认/error请求,返回ModelAndView
 *      DefaultErrorViewResolver
 *      StaticView:默认的错误View
 *
 * 前端控制器(DispatcherServlet):
 *      通过DispatcherServletAutoConfiguration->dispatcherServletRegistration方法来实现向容器中添加servlet
 *
 * 嵌入式Servlet容器自动配置
 *      ServletWebServerFactoryAutoConfiguration->ServletWebServerFactoryConfiguration->TomcatServletWebServerFactory
 *      BeanPostProcessorsRegistrar->WebServerFactoryCustomizerBeanPostProcessor
 *
 * 修改SpringBoot的默认配置:
 *      1. SpringBoot在自动配置很多组件的时候,先看容器中有没有用户自己配置的,如果有则使用用户配置的,如果没有,使用自动配置;如果可以有多个,则将用户配置的和自动配置的组合
 *      2. 在SpringBoot中会有非常多的xxxConfigurer帮助我们进行扩展配置
 *      3. 在SpringBoot中会有很多的xxxCustomizer帮助我们进行定制配置
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
@EnableRabbit
public class WebSpringBootApplication {

    public static void main(String[] args) {
        // 启动方式一
        //SpringApplication.run(WebSpringBootApplication.class, args);
        // 启动方式二
        ConfigurableApplicationContext run = new SpringApplicationBuilder(WebSpringBootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
