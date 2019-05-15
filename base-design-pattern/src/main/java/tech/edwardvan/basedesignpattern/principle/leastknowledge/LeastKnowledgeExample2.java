package tech.edwardvan.basedesignpattern.principle.leastknowledge;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则
 *
 * @author EdwardVan
 */
public class LeastKnowledgeExample2 {

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

        /**
         * 输出所有员工
         */
        public void printAllEmployee() {
            List<SubEmployee> allEmployee = this.getAllEmployee();
            for (SubEmployee subEmployee : allEmployee) {
                System.out.println(subEmployee.getId());
            }
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
            sub.printAllEmployee();
            List<Employee> allEmployee = this.getAllEmployee();
            for (Employee employee : allEmployee) {
                System.out.println(employee.getId());
            }
        }
    }

}
