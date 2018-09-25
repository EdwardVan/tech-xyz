package tech.edwardvan.webssmxml.model;

import java.util.List;
import java.util.Map;

public class StudentTest {
    private Integer id;
    private String name;
    private List<String> dream;
    private Map<String, Integer> score;
    private boolean graduation;

    public StudentTest() {
    }

    public StudentTest(boolean graduation) {
        this.graduation = graduation;
    }

    //测试spring init-method属性
    public void init(){
       this.id = 1;
    }

    //测试spring destroy-method属性
    public void destory(){
        System.out.println("StudentTest实例销毁后调用方法!");
    }

    //测试spring静态工厂创建对象
    public static StudentTest createStudentTest(){
        StudentTest st2 = new StudentTest();
        st2.setId(2);
        return st2;
    }

    //测试spring实例工厂创建对象
    public StudentTest getStudentTest(){
        StudentTest st3 = new StudentTest();
        st3.setId(3);
        return st3;
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
