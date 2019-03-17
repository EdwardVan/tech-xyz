package tech.edwardvan.webssmannotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Bean工厂后置处理器
 * <p>
 * Spring容器允许BeanFactoryPostProcessor在容器保存了所有bean定义信息之后且实例化任何bean之前读取bean的定义(配置元数据),并可以修改它
 * 同时可以定义多个BeanFactoryPostProcessor,通过设置'order'属性来确定各个BeanFactoryPostProcessor执行顺序
 *
 * @author EdwardVan
 */
@Component
public class BeanFactoryPostProcessorConfig implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanFactoryPostProcessorConfig.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        logger.debug("当前BeanFactory中有有{}个Bean定义", beanDefinitionCount);
    }
}
