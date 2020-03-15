package tech.edwardvan.testspringbootautoconfigure.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Bean后置处理器
 * <p>
 * beforeInitialization执行:{@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 * afterInitialization执行:{@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)}
 *
 * @author EdwardVan
 */
@Component
@Slf4j
public class TestBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
