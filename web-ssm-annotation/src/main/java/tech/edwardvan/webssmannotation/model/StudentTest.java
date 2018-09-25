package tech.edwardvan.webssmannotation.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

//等价于<bean name="studentTest" class="tech.edwardvan.webssmannotation.model.StudentTest">
@Component("studentTest")
//指定单例还是多例,默认单例
@Scope(scopeName = "prototype")
public class StudentTest {

    private Integer id;
    //直接通过反射得到Field设置值,不是通过对应的set方法
    @Value("Edward")
    private String name;
    private List<String> dream;
    private Map<String, Integer> score;
    private boolean graduation;

    public StudentTest() {
    }

    public StudentTest(boolean graduation) {
        this.graduation = graduation;
    }

    @PostConstruct//在构造之后调用,初始化方法,相当于init-method
    public void init(){
        this.graduation = true;
    }

    @PreDestroy//相当于destroy-method
    public void destory() {
        System.out.println("StudentTest实例销毁后调用方法!");
    }


    public Integer getId() {
        return id;
    }

    @Value("1")////通过set方法赋值
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
        return "StudentTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dream=" + dream +
                ", score=" + score +
                ", graduation=" + graduation +
                '}';
    }
}
