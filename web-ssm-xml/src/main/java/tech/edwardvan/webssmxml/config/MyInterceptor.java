package tech.edwardvan.webssmxml.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义SpringMVC拦截器
 *
 * @author EdwardVan
 */
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    /**
     * 调用 目标方法之前 被调用
     * 可以添加权限操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("MyInterceptor preHandle");
        return true;
    }
    /**
     * 调用 目标方法之后 视图渲染之前 被调用
     * 可以对ModelAndView做出修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("MyInterceptor postHandle");
    }
    /**
     * 调用 视图渲染之后 被调用
     * 可以添加释放资源操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("MyInterceptor afterCompletion");
    }
}
