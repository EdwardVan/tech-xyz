package tech.edwardvan.rbacmypermission.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import tech.edwardvan.rbacmypermission.common.RequestHolder;
import tech.edwardvan.rbacmypermission.model.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web项目配置
 *
 * @author EdwardVan
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取spring配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * 获取springmvc配置类
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 配置过滤器
     */
    @Override
    protected Filter[] getServletFilters() {

        //编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");

        //登录过滤器
        Filter loginFilter = (servletRequest, servletResponse, filterChain) -> {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            String url = req.getRequestURL().toString();
            if ((url.endsWith(".json") || url.endsWith(".page")) && !url.endsWith("/login.page")) {
                SysUser sysUser = (SysUser) req.getSession().getAttribute("user");
                if (sysUser == null) {
                    String path = "/login.jsp";
                    resp.sendRedirect(path);
                    return;
                }
                RequestHolder.add(sysUser);
                RequestHolder.add(req);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        };

        return new Filter[]{characterEncodingFilter, loginFilter};
    }
}