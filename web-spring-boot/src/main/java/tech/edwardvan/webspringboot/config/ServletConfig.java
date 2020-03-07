package tech.edwardvan.webspringboot.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.edwardvan.webspringboot.servlet.TestServlet;

/**
 * Servlet配置类
 *
 * @author EdwardVan
 */
@Configuration
/**
 * 方法一: @ServletComponentScan + @WebServlet @WebFilter @WebListener
 */
@ServletComponentScan(basePackages = "tech.edwardvan.webspringboot.servlet")
public class ServletConfig {

    /**
     * 方法二:实现ServletContextInitializer接口动态注册
     */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
//            TestServlet testServlet = new TestServlet();
//            ServletRegistration.Dynamic registration = servletContext.addServlet("testServlet", testServlet);
//            registration.addMapping("/testServlet");
        };
    }

    /**
     * 方式三:向容器中添加ServletRegistrationBean类
     * 原理:
     * {@link ServletRegistrationBean}实现了{@link ServletContextInitializer}接口
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new TestServlet(), "/testServlet");
    }
}
