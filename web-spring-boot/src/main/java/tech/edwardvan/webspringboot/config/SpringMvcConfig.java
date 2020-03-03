package tech.edwardvan.webspringboot.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import tech.edwardvan.webspringboot.converter.PropertiesHttpMessageConverter;
import tech.edwardvan.webspringboot.handler.PropertiesHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import tech.edwardvan.webspringboot.handler.PropertiesHandlerMethodReturnValueHandler;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * WebMvc扩展配置类
 * <p>
 * 不标注 @EnableWebMvc,既保留了所有的自动配置,也能用我们扩展的配置
 * 自动配置:{@link WebMvcAutoConfiguration.EnableWebMvcConfiguration}
 * 加载扩展:{@link WebMvcAutoConfiguration.EnableWebMvcConfiguration#setConfigurers}
 * <p>
 * 标注@EnableWebMvc,全面接管SpringMVC配置
 * 原理:
 * {@link EnableWebMvc} -> @Import(DelegatingWebMvcConfiguration.class)
 * 自动配置失效:{@link WebMvcAutoConfiguration} -> @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 * <p>
 * 视图内容协商
 * 视图解析器:{@link WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#viewResolver(BeanFactory)}
 * 策列顺序:{@link ContentNegotiationManagerFactoryBean#build()} -> 后缀 > 请求参数format > HTTP请求头Accept
 *
 * @author EdwardVan
 */
@Configuration
@Slf4j
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        // 获取当前 RequestMappingHandlerAdapter 所有的 Resolver 对象
        List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        ArrayList<HandlerMethodArgumentResolver> newResolvers = Lists.newArrayListWithCapacity(resolvers.size() + 1);
        // 添加 PropertiesHandlerMethodArgumentResolver 到集合首位
        newResolvers.add(new PropertiesHandlerMethodArgumentResolver());
        // 添加 已注册的 Resolver 对象集合
        newResolvers.addAll(resolvers);
        // 重新设置 Resolver 对象集合
        requestMappingHandlerAdapter.setArgumentResolvers(newResolvers);


        // 获取当前 RequestMappingHandlerAdapter 所有的 Handler 对象
        List<HandlerMethodReturnValueHandler> handlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        ArrayList<HandlerMethodReturnValueHandler> newHandlers = Lists.newArrayListWithCapacity(handlers.size() + 1);
        // 添加 PropertiesHandlerMethodReturnValueHandler 到集合首位
        newHandlers.add(new PropertiesHandlerMethodReturnValueHandler());
        // 添加 已注册的 Handler 对象集合
        newHandlers.addAll(handlers);
        // 重新设置 Handler 对象集合
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    /**
     * 添加视图映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/swagger-ui.html");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                log.info("This is SpringMvcConfig.addInterceptors()");
                return true;
            }
        });
    }

    /**
     * 配置视图内容协商
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
        //.defaultContentTypeStrategy(new ...) // 自定义一个默认的内容协商策略
        //.ignoreAcceptHeader(true) // 禁用Accept协商方式
        //.defaultContentType(MediaType.APPLICATION_JSON) // 它的效果是new FixedContentNegotiationStrategy(contentTypes)
        ;
    }

    /**
     * 添加自定义方法参数解析器
     * 原理:
     * {@link WebMvcAutoConfiguration.EnableWebMvcConfiguration#requestMappingHandlerAdapter(ContentNegotiationManager, FormattingConversionService, Validator)}
     * {@link WebMvcConfigurationSupport#getArgumentResolvers()}
     * {@link RequestMappingHandlerAdapter#getDefaultArgumentResolvers()}
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //注意:此方法无法添加解析器到队列第一个位置
//        resolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
    }

    /**
     * 添加HttpMessageConverter
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加到converters的末尾
//        converters.add(new PropertiesHttpMessageConverter());
        // 添加到converters的首位
        converters.set(0, new PropertiesHttpMessageConverter());
    }
}
