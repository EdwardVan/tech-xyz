package tech.edwardvan.webssmannotation.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

/**
 * 用注解的方式向spring容器中注册对象
 *
 * @author EdwardVan
 * <p>
 * Component注解 等价于<bean name="student" class="tech.edwardvan.webssmannotation.model.Student">
 * Value注解赋值:1. 基本数值 2. 可以写SpEL表达式,#{} 3. 可以写${}表达式取配置文件中的值
 */
@Component("student")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy
public class Student implements InitializingBean, DisposableBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    private Integer id;
    //直接通过反射得到Field设置值,不是通过对应的set方法
    @Value("Edward")
    private String name;
    private List<String> dream;
    private Map<String, Integer> score;
    private boolean graduation;
    private ApplicationContext applicationContext;

    public Student() {
        logger.debug("Student实例->构造方法.(顺序1)");
    }

    public Student(boolean graduation) {
        this.graduation = graduation;
    }

    @PostConstruct
    public void init() {
        logger.debug("Student实例->@PostConstruct.(顺序3)");
    }

    @PreDestroy
    public void destory() {
        logger.debug("Student实例->@PreDestroy");
    }

    @Override
    public void afterPropertiesSet() {
        logger.debug("Student实例->afterPropertiesSet().(顺序4)");
    }

    @Override
    public void destroy() {
        logger.debug("Student实例->destroy()");
    }

    public Integer getId() {
        return id;
    }

    @Value("1")//通过set方法赋值
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDream() {
        return dream;
    }

    public void setDream(List<String> dream) {
        this.dream = dream;
    }

    public Map<String, Integer> getScore() {
        return score;
    }

    public void setScore(Map<String, Integer> score) {
        this.score = score;
    }

    public boolean isGraduation() {
        return graduation;
    }

    public void setGraduation(boolean graduation) {
        this.graduation = graduation;
    }

    @Override
    public String toString() {
        applicationContext.publishEvent(new ApplicationEvent("发布Student.toString()事件") {
        });
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dream=" + dream +
                ", score=" + score +
                ", graduation=" + graduation +
                '}';
    }

    /**
     * 把Spring底层一些组件注入到自定义的Bean中
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
