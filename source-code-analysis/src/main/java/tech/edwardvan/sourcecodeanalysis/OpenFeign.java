package tech.edwardvan.sourcecodeanalysis;

import feign.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * OpenFeign
 *
 * @author EdwardVan
 */
public interface OpenFeign {

    /**
     * Client:{@link org.springframework.cloud.openfeign.ribbon.DefaultFeignLoadBalancedConfiguration#feignClient(CachingSpringLoadBalancerFactory, SpringClientFactory)}
     * FeignContext:{@link FeignAutoConfiguration#feignContext()}
     * Options:{@link FeignRibbonClientAutoConfiguration#feignRequestOptions()}
     * Decoder:{@link FeignClientsConfiguration#feignDecoder()}
     * Encoder:{@link FeignClientsConfiguration#feignEncoder()}
     * Contract:{@link FeignClientsConfiguration#feignContract(ConversionService)}
     * Retryer:{@link FeignClientsConfiguration#feignRetryer()}
     * Builder:{@link FeignClientsConfiguration#feignBuilder(Retryer)}
     */
    void 自动装配();

    /**
     * [扫描代理类]-入口:{@link org.springframework.cloud.openfeign.FeignClientsRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)}
     * 扫描指定包下的接口:{@link ClassPathScanningCandidateComponentProvider#findCandidateComponents(String)}
     * 将client的配置注册到beanDefinitionMap:{@link org.springframework.cloud.openfeign.FeignClientsRegistrar#registerClientConfiguration(BeanDefinitionRegistry, Object, Object)}
     * 将client注册到beanDefinitionMap:{@link org.springframework.cloud.openfeign.FeignClientsRegistrar#registerFeignClient(BeanDefinitionRegistry, AnnotationMetadata, Map)}
     * [扫描代理类]-出口
     * [生成代理类]-入口:{@link org.springframework.cloud.openfeign.FeignClientFactoryBean#getTarget()}
     * 初始化Builder:{@link org.springframework.cloud.openfeign.FeignClientFactoryBean#feign(FeignContext)}
     * 从容器中获取Client
     * 从容器中获取Targeter
     * [生成代理类核心]-入口:{@link feign.ReflectiveFeign#newInstance(Target)}
     * 解析接口方法注解信息:{@link feign.Contract.BaseContract#parseAndValidateMetadata(Class)}
     * 生成InvocationHandler:{@link InvocationHandlerFactory.Default#create(Target, Map)}
     * 生成代理类:{@link Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)}
     * [生成代理类核心]-出口
     * [生成代理类]-出口
     */
    void 生成代理对象();

    /**
     * [执行代理方法]-入口:{@link feign.ReflectiveFeign.FeignInvocationHandler#invoke(Object, Method, Object[])}
     * 获取请求模板:{@link feign.ReflectiveFeign.BuildTemplateByResolvingArgs#create(Object[])}
     * 执行请求拦截器:{@link feign.SynchronousMethodHandler#targetRequest(RequestTemplate)}
     * [执行请求]-入口:{@link LoadBalancerFeignClient#execute(Request, Request.Options)}
     * 负载均衡获取Server信息:{@link com.netflix.loadbalancer.reactive.LoadBalancerCommand#selectServer()}
     * 执行真正的请求
     * [执行请求]-出口
     * 创建Response
     * [执行代理方法]-出口
     */
    void 执行代理对象();
}
