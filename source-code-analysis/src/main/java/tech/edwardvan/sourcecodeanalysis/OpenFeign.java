package tech.edwardvan.sourcecodeanalysis;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
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
     * [扫描代理类]-入口:{@link org.springframework.cloud.openfeign.FeignClientsRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)}
     * 扫描指定包下的接口:{@link ClassPathScanningCandidateComponentProvider#findCandidateComponents(String)}
     * 将client注册到beanDefinitionMap:{@link org.springframework.cloud.openfeign.FeignClientsRegistrar#registerFeignClient(BeanDefinitionRegistry, AnnotationMetadata, Map)}
     * [扫描代理类]-出口
     * [生成代理类]-入口:{@link org.springframework.cloud.openfeign.FeignClientFactoryBean#getTarget()}
     * 初始化feign环境:{@link org.springframework.cloud.openfeign.FeignClientFactoryBean#feign(org.springframework.cloud.openfeign.FeignContext)}
     * 从容器中获取Client:{@link org.springframework.cloud.openfeign.FeignClientFactoryBean#getOptional(org.springframework.cloud.openfeign.FeignContext, Class)}
     * 从容器中获取Targeter
     * [生成代理类核心]-入口:{@link feign.ReflectiveFeign#newInstance(feign.Target)}
     * 解析接口方法注解信息:{@link feign.Contract.BaseContract#parseAndValidateMetadata(java.lang.Class)}
     * 生成InvocationHandler:{@link feign.InvocationHandlerFactory#create(feign.Target, java.util.Map)}
     * 生成代理类:{@link Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)}
     * [生成代理类核心]-出口
     * [生成代理类]-出口
     */
    void 生成代理对象();

    /**
     * [执行代理方法]-入口:{@link feign.ReflectiveFeign.FeignInvocationHandler#invoke(Object, Method, Object[])}
     * 获取请求模板:{@link feign.ReflectiveFeign.BuildTemplateByResolvingArgs#create(Object[])}
     * 执行请求拦截器:{@link feign.SynchronousMethodHandler#targetRequest(feign.RequestTemplate)}
     * 执行请求:{@link feign.Client#execute(feign.Request, feign.Request.Options)}
     * [执行代理方法]-出口
     */
    void 执行代理对象();
}
