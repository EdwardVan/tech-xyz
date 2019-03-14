package tech.edwardvan.webssmannotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Bean后置处理器
 *
 * @author EdwardVan
 * <p>
 * BeanPostProcessor原理
 * 1. populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * 2. initializeBean{
 *      applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *      invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化(@PostConstruct/InitializingBean接口)
 *      applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *   }
 *
 * Spring底层对 BeanPostProcessor 的使用
 *      bean赋值
 *      注入其他组件
 *      Autowired注解
 *      生命周期注解功能
 *      Async注解
 */
@Component
public class BeanPostProcessorConfig implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanPostProcessorConfig.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("student".equals(beanName)) {
            logger.debug("postProcessBeforeInitialization->{}.(顺序2)", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("student".equals(beanName)) {
            logger.debug("postProcessAfterInitialization->{}.(顺序5)", beanName);
        }
        return bean;
    }
}
