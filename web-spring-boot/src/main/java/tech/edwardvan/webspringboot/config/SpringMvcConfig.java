package tech.edwardvan.webspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc扩展配置类
 *
 * 不标注@EnableWebMvc,既保留了所有的自动配置,也能用我们扩展的配置
 * 原理:
 *      DelegatingWebMvcConfiguration -> setConfigurers(List<WebMvcConfigurer> configurers) 从容器中获取所有的WebMvcConfigurer
 *
 * 标注@EnableWebMvc,全面接管SpringMVC配置
 * 原理:
 *      WebMvcAutoConfiguration -> @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 *
 * @author EdwardVan
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 添加视图映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/swagger-ui.html");
    }
}
