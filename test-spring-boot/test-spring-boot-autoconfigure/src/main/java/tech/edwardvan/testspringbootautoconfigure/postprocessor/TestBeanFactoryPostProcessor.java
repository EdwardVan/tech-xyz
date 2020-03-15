package tech.edwardvan.testspringbootautoconfigure.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Bean工厂后置处理器
 * <p>
 * Spring容器允许BeanFactoryPostProcessor在容器保存了所有bean定义信息之后且实例化任何bean之前读取bean的定义(配置元数据),并可以修改它
 * 同时可以定义多个BeanFactoryPostProcessor,通过设置'order'属性来确定各个BeanFactoryPostProcessor执行顺序
 * <p>
 * 执行:{@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(Collection, ConfigurableListableBeanFactory)}
 *
 * @author EdwardVan
 */
@Component
@Slf4j
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
