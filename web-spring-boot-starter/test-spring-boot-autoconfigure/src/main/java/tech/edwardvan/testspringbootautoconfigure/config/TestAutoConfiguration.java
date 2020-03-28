package tech.edwardvan.testspringbootautoconfigure.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import tech.edwardvan.testspringbootautoconfigure.condition.ConditionalTest;
import tech.edwardvan.testspringbootautoconfigure.importclass.TestImportBeanDefinitionRegistrar;
import tech.edwardvan.testspringbootautoconfigure.model.Test;
import tech.edwardvan.testspringbootautoconfigure.model.Test2;
import tech.edwardvan.testspringbootautoconfigure.properties.TestProperties;
import tech.edwardvan.testspringbootautoconfigure.importclass.TestImportSelector;

/**
 * 自动配置类
 *
 * @author EdwardVan
 */
@Configuration
/**
 * 自定义条件装配
 */
@ConditionalTest("test")
/**
 * 将TestProperties加入到ioc容器中,并将配置文件中对应的值和TestProperties绑定起来
 */
@EnableConfigurationProperties(TestProperties.class)
@ComponentScan("tech.edwardvan.testspringbootautoconfigure.postprocessor")
@Import({TestImportSelector.class, TestImportBeanDefinitionRegistrar.class})
public class TestAutoConfiguration {

    TestProperties testProperties;

    /**
     * 只有一个有参构造器的情况下,参数的值就会从容器中取值
     */
    public TestAutoConfiguration(TestProperties testProperties) {
        this.testProperties = testProperties;
    }

    /**
     * 当spring:profiles:active为dev时,该对象才会创建
     */
    @Bean
    @Profile("dev")
    public Test test() {
        return new Test();
    }

    /**
     * 工厂Bean
     * 获取Test2:applicationContext.getBean("test2")
     * 获取FactoryBean<Test2>:applicationContext.getBean("&test2")
     */
    @Bean
    public FactoryBean<Test2> test2() {
        return new FactoryBean<>() {
            @Override
            public Test2 getObject() {
                return new Test2();
            }

            @Override
            public Class<?> getObjectType() {
                return Test2.class;
            }
        };
    }
}
