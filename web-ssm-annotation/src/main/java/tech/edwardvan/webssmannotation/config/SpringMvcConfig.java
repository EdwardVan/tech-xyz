package tech.edwardvan.webssmannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 等价于 <mvc:annotation-driven/>
@EnableWebMvc
@ComponentScan(basePackages = "tech.edwardvan.webssmannotation.controller")
@Import({SwaggerConfig.class})
public class SpringMvcConfig implements WebMvcConfigurer {

    // 等价于 <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置静态资源处理
        //对静态资源的请求转发到容器缺省的servlet,而不使用DispatcherServlet
        configurer.enable("default");
    }
}
