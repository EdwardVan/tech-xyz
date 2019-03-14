package tech.edwardvan.webssmannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMvc配置类
 *
 * @author EdwardVan
 * <p>
 * EnableWebMvc注解 等价于 <mvc:annotation-driven/>
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "tech.edwardvan.webssmannotation",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
        }
)
@Import(SwaggerConfig.class)
public class SpringMvcConfig implements WebMvcConfigurer {

    // 等价于 <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置静态资源处理
        //对静态资源的请求转发到容器缺省的servlet,而不使用DispatcherServlet
        configurer.enable("default");
    }
}
