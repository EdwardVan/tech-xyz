package tech.edwardvan.basedesignpattern.principle.dependencyinversion;

/**
 * 依赖倒置原则
 *
 * @author EdwardVan
 */
public class DependenceInversionExample2 {
    public static void main(String[] args) {
        Student student = new Student();
        student.studyCourse(new JavaCourse());
        student.studyCourse(new PythonCourse());
    }

    /**
     * 学生
     */
    public static class Student {
        /**
         * 学习Java课程
         * 面向接口编程
         * 优点:当学生想学习Python课程时,不需要修改Student类代码,
         */
        public void studyCourse(ICourse course) {
            course.studyCourse();
        }
    }

    /**
     * 课程接口
     */
    public interface ICourse {
        /**
         * 学习课程
         */
        void studyCourse();
    }

    /**
     * Java课程
     */
    public static class JavaCourse implements ICourse {
        @Override
        public void studyCourse() {
            System.out.println("学习Java课程");
        }
    }

    /**
     * Python课程
     */
    public static class PythonCourse implements ICourse {
        @Override
        public void studyCourse() {
            System.out.println("学习Python课程");
        }
    }
}
