package tech.edwardvan.basejava.nested;

import lombok.extern.slf4j.Slf4j;

/**
 * 局部内部类
 * 1.局部内部类只能在定义该内部类的方法内实例化,不可以在此方法外对其实例化.
 * 2.局部内部类对象不能使用该内部类所在方法的非final局部变量.
 */
@Slf4j
public class NonStaticNestedClassesExample2 {

    private int outer_i = 1;
    private int outer_j = 100;


    public void f() {

        final int i = 1;

        /**
         * 定义在方法内部
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
                log.info("inner_f1->outer_j:{}", NonStaticNestedClassesExample2.this.outer_j);
                // 可以访问外部类的局部变量(即方法内的变量),但是变量必须是final的
                log.info("inner_f1->i:{}", i);

            }
        }
        new Inner().inner_f1();
    }

    public static void main(String[] args) {
        // 访问局部内部类必须先有外部类对象
        NonStaticNestedClassesExample2 out = new NonStaticNestedClassesExample2();
        out.f();
    }
}
