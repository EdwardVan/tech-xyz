package tech.edwardvan.rbacmypermission.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import tech.edwardvan.rbacmypermission.common.RequestHolder;
import tech.edwardvan.rbacmypermission.common.ServerResponse;
import tech.edwardvan.rbacmypermission.model.SysUser;
import tech.edwardvan.rbacmypermission.service.SysUserService;
import tech.edwardvan.rbacmypermission.util.ApplicationContextUtil;
import tech.edwardvan.rbacmypermission.util.JsonUtil;

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

        //登录&权限过滤器
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

                //权限控制
                SysUserService sysUserService = ApplicationContextUtil.getBean(SysUserService.class);
                if (!sysUserService.hasUrlAcl(url)) {
                    noAuth(req, resp);
                    return;
                }

                filterChain.doFilter(servletRequest, servletResponse);

            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        };

        return new Filter[]{characterEncodingFilter, loginFilter};
    }

    /**
     * 无权限访问时处理
     */
    private void noAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if (servletPath.endsWith(".json")) {
            ServerResponse serverResponse = ServerResponse.error("没有访问权限，如需要访问，请联系管理员");
            response.setHeader("Content-Type", "application/json");
            response.getWriter().print(JsonUtil.beanToJson(serverResponse));
            return;
        } else {
            String path = "/noAuth.jsp";
            response.sendRedirect(path);
            return;
        }
    }
}