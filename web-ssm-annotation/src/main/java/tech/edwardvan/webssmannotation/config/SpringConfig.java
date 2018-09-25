package tech.edwardvan.webssmannotation.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "tech.edwardvan.webssmannotation", excludeFilters = {@ComponentScan.Filter(value = Controller.class)})
//等价于<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
@EnableAspectJAutoProxy
//等价于<tx:annotation-driven></tx:annotation-driven>
@EnableTransactionManagement
public class SpringConfig {

    /**
     * 必须加上static
     */
    @Bean
    public static PropertyPlaceholderConfigurer loadProperties() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        PathResource resource = new PathResource(System.getenv().get("CONFIG_PATH")+"dbConfig/web-ssm-annotation.properties");
        configurer.setLocations(resource);
        return configurer;
    }
}
