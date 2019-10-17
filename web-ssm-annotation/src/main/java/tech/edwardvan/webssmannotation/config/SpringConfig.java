package tech.edwardvan.webssmannotation.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tech.edwardvan.webssmannotation.model.User;

/**
 * Spring配置类
 *
 * @author EdwardVan
 * <p>
 * Configuration注解 等价于 <beans></beans>
 * Bean注解 等价于 <bean></bean>
 * EnableAspectJAutoProxy注解 等价于 <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * EnableTransactionManagement注解 等价于 <tx:annotation-driven></tx:annotation-driven>
 *
 * 给容器中注册组件的方式
 *      1. @Configuration + @Bean, 通常用于导入的第三方包里面的类
 *      2. 包扫描 + 组件标注注解 (@Controller/@Service/@Repository/@Component),通常用于自己编写的类
 *      3. @Import
 *           1) @Import(要导入到容器中的组件);容器中就会自动注册这个组件,id默认是全类名
 *           2) @Import(一个配置类);容器中就会自动注册这个配置类中的@Bean
 *           3) ImportSelector:返回需要导入的组件的全类名数组
 *      	 4) ImportBeanDefinitionRegistrar:手动注册bean到容器中
 *      4. 使用Spring提供的FactoryBean(工厂Bean)
 *      		1) 默认获取到的是工厂bean调用getObject创建的对象
 *      		2) 要获取工厂Bean本身,我们需要给id前面加一个&
 *
 * 指定Bean的初始化和销毁方法的方式
 *      1. 通过@Bean指定init-method和destroy-method
 *      2. 通过让Bean实现InitializingBean接口(定义初始化逻辑)和DisposableBean接口(定义销毁逻辑)
 *      3. 使用JSR250相关注解,@PostConstruct注解(在bean创建完成并且属性赋值完成后执行)和@PreDestroy注解(在容器销毁bean之前执行)
 *      4. BeanPostProcessor(bean的后置处理器),在bean初始化前后进行一些处理工作
 *              1) postProcessBeforeInitialization:在初始化之前工作
 *      		2) postProcessAfterInitialization:在初始化之后工作
 *
 */
@Configuration
@ComponentScan(
        basePackages = "tech.edwardvan.webssmannotation",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                        SwaggerConfig.class,
                        SpringMvcConfig.class
                })
        }
)
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {

    /**
     * 条件化配置注册bean
     */
    @Conditional({UserCondition.class})
    @Bean
    public User conditionalUser() {
        User user = new User();
        user.setUsername("conditionalUser");
        return user;
    }

    /**
     * 工厂注册Bean
     * 注册到容器中的bean实际上是new UserFactoryBean().getObject()返回的对象,而不是new UserFactoryBean()对象
     */
    @Bean
    public UserFactoryBean userFactoryBean(){
        return new UserFactoryBean();
    }
}
