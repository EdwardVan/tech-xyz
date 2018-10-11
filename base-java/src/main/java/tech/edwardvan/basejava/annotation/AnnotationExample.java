package tech.edwardvan.basejava.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationExample {

    public static void main(String[] args) throws Exception {
        // 注解获取属性上的注解
        Field testField = AnnotationExample.class.getField("test");
        for (Annotation annotation : testField.getDeclaredAnnotations()) {
            System.out.println(annotation);
        }
        // 注解获取方法上的注解
        Method testMethod = AnnotationExample.class.getMethod("test");
        for (Annotation annotation : testMethod.getDeclaredAnnotations()) {
            System.out.println(annotation);
        }
    }

    @TestAnn(1)
    public String test;

    @Deprecated
    @TestAnn(value = 2, name = {"1", "2", "3"})
    public void test() {

    }

    //声明注解
    //元注解是标记其他注解的注解
    /*
    @Target用来约束注解可以应用的地方(如方法、类或字段),其中ElementType是枚举类型
    当注解未指定Target值时,则此注解可以用于任何元素之上,多个值使用{}包含并用逗号隔开
    */
    @Target({ElementType.FIELD, ElementType.METHOD})
    /*
    @Retention用来约束注解的生命周期,分别有三个值,源码级别(source),类文件级别(class)或者运行时级别(runtime),其含有如下:
        SOURCE:注解将被编译器丢弃(该类型的注解信息只会保留在源码里,源码经过编译后,注解信息会被丢弃,不会保留在编译好的class文件里)
        CLASS:注解在class文件中可用,但会被VM丢弃(该类型的注解信息会保留在源码里和class文件里,在执行的时候,不会加载到虚拟机中)
        RUNTIME:注解信息将在运行期(JVM)也保留,因此可以通过反射机制读取注解的信息(源码、class文件和执行的时候都有注解的信息)
    当注解未定义Retention值时,默认值是CLASS
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnn {
        /*
        快捷方式就是注解中定义了名为value的元素
        在使用该注解时,如果该元素是唯一需要赋值的一个元素,那么此时无需使用key=value的语法,而只需在括号内给出value元素所需的值即可
         */
        int value() default 0;

        String[] name() default "";
    }
}
