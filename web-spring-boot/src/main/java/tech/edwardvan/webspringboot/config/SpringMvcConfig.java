package tech.edwardvan.webspringboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebMvc扩展配置类
 * <p>
 * 不标注 @EnableWebMvc,既保留了所有的自动配置,也能用我们扩展的配置
 * 自动配置:{@link WebMvcAutoConfiguration.EnableWebMvcConfiguration}
 * 加载扩展:{@link WebMvcAutoConfiguration.EnableWebMvcConfiguration#setConfigurers}
 * <p>
 * 标注@EnableWebMvc,全面接管SpringMVC配置
 * 原理:
 * {@link EnableWebMvc} -> @Import(DelegatingWebMvcConfiguration.class)
 * 自动配置失效:{@link WebMvcAutoConfiguration} -> @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 * <p>
 * 视图内容协商
 * 视图解析器:{@link WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#viewResolver(BeanFactory)}
 * 策列顺序:{@link ContentNegotiationManagerFactoryBean#build()} -> 后缀 > 请求参数format > HTTP请求头Accept
 *
 * @author EdwardVan
 */
@Configuration
@Slf4j
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 添加视图映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/swagger-ui.html");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                log.info("This is SpringMvcConfig.addInterceptors()");
                return true;
            }
        });
    }

    /**
     * 配置视图内容协商
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
        //.defaultContentTypeStrategy(new ...) // 自定义一个默认的内容协商策略
        //.ignoreAcceptHeader(true) // 禁用Accept协商方式
        //.defaultContentType(MediaType.APPLICATION_JSON) // 它的效果是new FixedContentNegotiationStrategy(contentTypes)
        ;
    }
}
