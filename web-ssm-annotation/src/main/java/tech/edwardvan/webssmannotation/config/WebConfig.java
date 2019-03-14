package tech.edwardvan.webssmannotation.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * web项目配置
 * <p>
 * 容器在启动应用的时候,会扫描每一个jar包里面META-INF/services/javax.servlet.ServletContainerInitializer文件中指定的实现类,
 * 启动并运行这个实现类的方法,传入感兴趣的类型
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
     * 获取编码处理过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        return new Filter[]{characterEncodingFilter};
    }
}

/*
方式二:
public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 编码处理
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        // spring配置
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(SpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(springContext));
        // springmvc配置
        AnnotationConfigWebApplicationContext springMvcConfig = new AnnotationConfigWebApplicationContext();
        springMvcConfig.register(SpringMvcConfig.class);
        springMvcConfig.refresh();
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("springmvc", new DispatcherServlet(springMvcConfig));
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

    }
}*/
