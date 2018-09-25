package tech.edwardvan.webssmannotation.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 编码处理
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        FilterRegistration.Dynamic filterRegistration  = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        // spring配置
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(SpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(springContext));
        // springmvc配置
        AnnotationConfigWebApplicationContext springMvcConfig = new AnnotationConfigWebApplicationContext();
        springMvcConfig.register(SpringMvcConfig.class);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("springmvc", new DispatcherServlet(springMvcConfig));
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

    }
}