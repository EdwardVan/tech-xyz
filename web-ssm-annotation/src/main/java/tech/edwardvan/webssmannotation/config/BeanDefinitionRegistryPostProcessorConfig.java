package tech.edwardvan.webssmannotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanDefinitionRegistryPostProcessor配置类
 * <p>
 * 优先于BeanFactoryPostProcessor执行,可以向BeanDefinitionRegistry中注册组件
 *
 * @author EdwardVan
 */
@Component
public class BeanDefinitionRegistryPostProcessorConfig implements BeanDefinitionRegistryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanDefinitionRegistryPostProcessorConfig.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        logger.debug("BeanDefinitionRegistryPostProcessorConfig.postProcessBeanDefinitionRegistry()");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.debug("BeanDefinitionRegistryPostProcessorConfig.postProcessBeanFactory()");
    }
}
