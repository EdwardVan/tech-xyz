package tech.edwardvan.webspringboot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 模拟SpringBoot自动配置类
 *
 * Conditional派生注解介绍:
 *      ConditionalOnJava              系统的java版本是否符合要求
 *      ConditionalOnBean              容器中存在指定Bean
 *      ConditionalOnMissingBean       容器中不存在指定Bean
 *      ConditionalOnExpression        满足SpEL表达式指定
 *      ConditionalOnClass             系统中有指定的类
 *      ConditionalOnMissingClass      系统中没有指定的类
 *      ConditionalOnSingleCandidate   容器中只有一个指定的Bean，或者这个Bean是首选Bean
 *      ConditionalOnProperty          系统中指定的属性是否有指定的值
 *      ConditionalOnResource          类路径下是否存在指定资源文件
 *      ConditionalOnWebApplication    当前是web环境
 *      ConditionalOnNotWebApplication 当前不是web环境
 *      ConditionalOnJndi              JNDI存在指定项
 *
 * @author EdwardVan
 */
@Configuration
@Conditional({TestCondition.class})
//将配置文件中对应的值和TestProperties绑定起来;并将TestProperties加入到ioc容器中
@EnableConfigurationProperties(TestProperties.class)
public class TestConfiguration {

    TestProperties testProperties;

    /**
     * 只有一个有参构造器的情况下,参数的值就会从容器中取值
     */
    public TestConfiguration(TestProperties testProperties) {
        this.testProperties = testProperties;
    }

    @Override
    public String toString() {
        return "TestConfiguration{" +
                "testProperties=" + testProperties +
                '}';
    }
}
