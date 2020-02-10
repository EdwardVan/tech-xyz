package tech.edwardvan.testspringbootautoconfigure.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 自定义Conditional派生注解
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
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(TestCondition.class)
public @interface ConditionalTest {

    String value() default "";
}
