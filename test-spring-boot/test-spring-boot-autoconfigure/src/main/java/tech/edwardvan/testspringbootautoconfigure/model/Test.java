package tech.edwardvan.testspringbootautoconfigure.model;

import lombok.Data;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 对象核心创建步骤
 * <p>
 * 实例化剩余所有对象:{@link DefaultListableBeanFactory#preInstantiateSingletons()}
 * 获取一个实例对象:{@link AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}
 * 实例化对象入口:{@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}
 * 实例化对象:{@link AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])}
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
