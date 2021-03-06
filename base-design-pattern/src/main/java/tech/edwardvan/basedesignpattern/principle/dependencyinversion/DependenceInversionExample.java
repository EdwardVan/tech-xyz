package tech.edwardvan.basedesignpattern.principle.dependencyinversion;

/**
 * 依赖倒置原则
 * <p>
 * 定义:高层模块不应该依赖低层模块,二者都应该依赖其抽象;抽象不应该依赖细节;细节应该依赖抽象.
 * 问题由来:类A直接依赖类B,假如要将类A改为依赖类C,则必须通过修改类A的代码来达成.这种场景下,类A一般是高层模块,负责复杂的业务逻辑;类B和类C是低层模块,负责基本的原子操作;假如修改类A,会给程序带来不必要的风险.
 * 解决方案:将类A修改为依赖接口I,类B和类C各自实现接口I,类A通过接口I间接与类B或者类C发生联系,则会大大降低修改类A的几率.
 *
 * @author EdwardVan
 */
public class DependenceInversionExample {
    public static void main(String[] args) {
        Student student = new Student();
        student.studyCourse(new JavaCourse());
    }

    /**
     * 学生
     */
    public static class Student {
        /**
         * 学习Java课程
         * 面向实现编程
         * 缺点:当学生想学习Python课程时,则需要修改Student类代码
         */
        public void studyCourse(JavaCourse javaCourse) {
            javaCourse.studyCourse();
        }
    }

    /**
     * Java课程
     */
    public static class JavaCourse {
        public void studyCourse() {
            System.out.println("学习Java课程");
        }
    }

    /**
     * Python课程
     */
    public static class PythonCourse {
        public void studyCourse() {
            System.out.println("学习Python课程");
        }
    }
}
