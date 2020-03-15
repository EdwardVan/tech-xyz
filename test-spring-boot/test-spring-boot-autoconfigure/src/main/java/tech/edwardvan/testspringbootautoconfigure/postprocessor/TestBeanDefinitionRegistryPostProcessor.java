package tech.edwardvan.testspringbootautoconfigure.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * BeanDefinitionRegistryPostProcessor
 * <p>
 * 执行:{@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanDefinitionRegistryPostProcessors(Collection, BeanDefinitionRegistry)}
 *
 * @author EdwardVan
 */
@Component
@Slf4j
public class TestBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
