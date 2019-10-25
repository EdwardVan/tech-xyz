package tech.edwardvan.rbacmypermission.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * SpringMvc配置类
 *
 * @author EdwardVan
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "tech.edwardvan.rbacmypermission.controller")
@Import({SpringExceptionResolver.class, SwaggerConfig.class})
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置静态资源处理
        configurer.enable("default");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/views/", ".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorConfig());
    }

    @Bean
    public static MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }

    /**
     * 配置beanName视图解析器
     */
    @Bean
    public static BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(100);
        return beanNameViewResolver;
    }

}
