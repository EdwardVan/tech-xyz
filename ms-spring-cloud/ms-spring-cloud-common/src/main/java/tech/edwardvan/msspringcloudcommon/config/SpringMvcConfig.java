package tech.edwardvan.msspringcloudcommon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 全局WebMvc配置类
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
}
