package tech.edwardvan.webspringboot.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import tech.edwardvan.webspringboot.converter.PropertiesHttpMessageConverter;
import tech.edwardvan.webspringboot.handler.PropertiesHandlerMethodArgumentResolver;
import tech.edwardvan.webspringboot.handler.PropertiesHandlerMethodReturnValueHandler;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * WebMvc扩展配置类
 * <p>
 * SpringMVC处理请求核心步骤:
 * 处理器映射器:{@link DispatcherServlet#getHandler(HttpServletRequest)}
 * 处理器适配器:{@link DispatcherServlet#getHandlerAdapter(Object)}
 * 执行适配入口:{@link RequestMappingHandlerAdapter#handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod)}
 * 解析方法参数:{@link InvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)}
 * 寻找方法参数解析器:{@link HandlerMethodArgumentResolverComposite#getArgumentResolver(MethodParameter)}
 * 执行方法参数解析器:{@link HandlerMethodArgumentResolver#resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory)}
 * 执行方法且返回结果:{@link InvocableHandlerMethod#doInvoke(Object...)}
 * 寻找方法返回值处理器{@link HandlerMethodReturnValueHandlerComposite#selectHandler(Object, MethodParameter)}
 * 处理返回值:{@link HandlerMethodReturnValueHandler#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)}
 * 视图内容协商器解析得到View:{@link ContentNegotiatingViewResolver#resolveViewName(String, Locale)}
 * 输出结果:{@link View#render(Map, HttpServletRequest, HttpServletResponse)}
 * <p>
 * 标注与不标注@EnableWebMvc的区别
 * 不标注 @EnableWebMvc,既保留了所有的自动配置,也能用我们扩展的配置
 * 自动配置:{@link WebMvcAutoConfiguration.EnableWebMvcConfiguration}
 * 加载扩展:{@link WebMvcAutoConfiguration.EnableWebMvcConfiguration#setConfigurers}
 * 标注@EnableWebMvc,此类全面接管SpringMVC配置
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
