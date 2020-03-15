package tech.edwardvan.testspringbootautoconfigure.model;

import lombok.Data;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 对象核心创建步骤:
 * {@link DefaultListableBeanFactory#preInstantiateSingletons()}
 * {@link AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}
 * {@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}
 * 实例化:{@link AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])}
 * 检查Aware相关方法:{@link AbstractAutowireCapableBeanFactory#invokeAwareMethods(String, Object)}
 * BeanPostProcessor前置处理:{@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 * 自定义初始化:{@link AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)}
 * BeanPostProcessor后置处理:{@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)}
 *
 * @author EdwardVan
 */
@Data
public class Test {
    String test = "test";
}
