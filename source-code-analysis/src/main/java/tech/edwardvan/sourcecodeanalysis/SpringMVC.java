package tech.edwardvan.sourcecodeanalysis;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

public interface SpringMVC {
    /**
     * SpringMVC处理请求步骤
     * <p>
     * 处理器映射器执行:{@link DispatcherServlet#getHandler(HttpServletRequest)}
     * 获取处理器适配器:{@link DispatcherServlet#getHandlerAdapter(Object)}
     * [执行适配]-入口:{@link RequestMappingHandlerAdapter#handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod)}
     * [解析方法参数]-入口:{@link InvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)}
     * 寻找参数解析器:{@link HandlerMethodArgumentResolverComposite#getArgumentResolver(MethodParameter)}
     * 执行参数解析器:{@link HandlerMethodArgumentResolver#resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory)}
     * [解析方法参数]-出口
     * 执行方法且返回结果:{@link InvocableHandlerMethod#doInvoke(Object...)}
     * [解析方法返回值]-入口:{@link HandlerMethodReturnValueHandlerComposite#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)}
     * 寻找返回值处理器:{@link HandlerMethodReturnValueHandlerComposite#selectHandler(Object, MethodParameter)}
     * 执行返回值处理器:{@link HandlerMethodReturnValueHandler#handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)}
     * [解析方法返回值]-出口
     * [执行适配]-出口
     * 视图内容协商器解析得到View:{@link ContentNegotiatingViewResolver#resolveViewName(String, Locale)}
     * 输出结果:{@link View#render(Map, HttpServletRequest, HttpServletResponse)}
     */
    void SpringMVC处理请求步骤();
}
