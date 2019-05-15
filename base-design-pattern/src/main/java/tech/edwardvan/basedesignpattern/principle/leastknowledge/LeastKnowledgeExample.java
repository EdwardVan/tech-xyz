package tech.edwardvan.basedesignpattern.principle.leastknowledge;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则(最少知识原则)
 * <p>
 * 定义:一个对象应该对其他对象保持最少的了解.
 * 简单定义:只与直接的朋友通信.成员变量&方法参数&方法返回值中的类为直接的朋友,而出现在局部变量中的类则不是直接的朋友.陌生的类最好不要作为局部变量的形式出现在类的内部.
 * 问题由来:类与类之间的关系越密切,耦合度越大,当一个类发生改变时,对另一个类的影响也越大.
 * 解决方案:尽量降低类与类之间的耦合.
 *
 * @author EdwardVan
 */
public class LeastKnowledgeExample {

    public static void main(String[] args) {
        CompanyManager e = new CompanyManager();
        e.printAllEmployee(new SubCompanyManager());
    }

    /**
     * 总公司员工
     */
    public static class Employee {
        private String id;

        public Employee(String id) {
            this.id = id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    /**
     * 分公司员工
     */
    public static class SubEmployee {
        private String id;

        public SubEmployee(String id) {
            this.id = id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    /**
     * 子公司管理
     */
    public static class SubCompanyManager {
        /**
         * 获取公司所有员工
         */
        public List<SubEmployee> getAllEmployee() {
            List<SubEmployee> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                //为分公司人员按顺序分配一个ID
                list.add(new SubEmployee("分公司" + i));
            }
            return list;
        }
    }

    /**
     * 总公司管理
     */
    public static class CompanyManager {
        /**
         * 获取公司所有员工
         */
        public List<Employee> getAllEmployee() {
            List<Employee> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                //为总公司人员按顺序分配一个ID
                list.add(new Employee("总公司" + i));
            }
            return list;
        }

        /**
         * 输出所有员工
         */
        public void printAllEmployee(SubCompanyManager sub) {
            //SubEmployee为陌生的类,陌生的类最好不要作为局部变量的形式出现在类的内部
            List<SubEmployee> subAllEmployee = sub.getAllEmployee();
            for (SubEmployee e : subAllEmployee) {
                System.out.println(e.getId());
            }

            List<Employee> allEmployee = this.getAllEmployee();
            for (Employee e : allEmployee) {
                System.out.println(e.getId());
            }
        }
    }

}
