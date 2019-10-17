package tech.edwardvan.rbacmypermission.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring配置类
 *
 * @author EdwardVan
 */
@Configuration
@ComponentScan(
        basePackages = "tech.edwardvan.rbacmypermission",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                        SpringMvcConfig.class
                })
        }
)
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {
}
