package tech.edwardvan.basejava.nested;

import lombok.extern.slf4j.Slf4j;

/**
 * 成员内部类
 */
@Slf4j
public class NonStaticNestedClassesExample {
    private static int outer_i = 1;
    private int outer_j = 10;
    private int outer_k = 20;

    public static void outer_f1() {
    }

    public void outer_f2() {
    }

    /**
     * 成员内部类中,可以访问外部类的所有成员
     */
    class Inner {
        //内部类中不允许定义静态变量
        // static int inner_i = 100;
        /**
         * 内部类和外部类的实例变量可以共存
         */
        int inner_j = 100;

        void inner_f1() {
            // 在内部类中访问内部类自己的变量直接用变量名
            log.info("inner_f1->inner_j:{}", inner_j);
            // 在内部类中访问内部类自己的变量也可以用this.变量名
            log.info("inner_f1->inner_j:{}", this.inner_j);
            // 在内部类中访问外部类静态变量
            log.info("inner_f1->outer_i:{}", outer_i);
            // 在内部类中访问外部类中实例变量用外部类名.this.变量名
            log.info("inner_f1->outer_j:{}", NonStaticNestedClassesExample.this.outer_j);
            // 如果内部类中没有与外部类同名的变量,则可以直接用变量名访问外部类变量
            log.info("inner_f1->outer_k:{}", outer_k);
            outer_f1();
            outer_f2();
        }
    }

    /**
     * 外部类的非静态方法访问成员内部类
     */
    public void outer_f3() {
        Inner inner = new Inner();
        inner.inner_f1();
    }

    /**
     * 外部类的静态方法访问成员内部类,与在外部类外部访问成员内部类一样
     */
    public static void outer_f4() {
        // step1 建立外部类对象
        NonStaticNestedClassesExample out = new NonStaticNestedClassesExample();
        // step2 根据外部类对象建立内部类对象
        Inner inner = out.new Inner();
        // step3 访问内部类的方法
        inner.inner_f1();
    }

    public static void main(String[] args) {
        //该语句的输出结果和下面三条语句的输出结果一样
        outer_f4();
    }
}