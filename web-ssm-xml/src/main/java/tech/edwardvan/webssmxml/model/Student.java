package tech.edwardvan.webssmxml.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * spring创建对象测试类
 *
 * @author EdwardVan
 */
public class Student {

    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    private Integer id;
    private String name;
    private List<String> dream;
    private Map<String, Integer> score;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    //测试init-method属性
    public void init() {
        logger.info("Student.init()方法执行");
    }

    //测试destroy-method属性
    public void destory() {
        logger.info("Student.destory()方法执行");
    }

    //测试静态工厂创建对象
    public static Student staticFactory() {
        Student student = new Student();
        student.setId(2);
        return student;
    }

    //测试实例工厂创建对象
    public Student instanceFactory() {
        Student student = new Student();
        student.setId(3);
        return student;
    }

    public Integer getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dream=" + dream +
                ", score=" + score +
                '}';
    }
}
