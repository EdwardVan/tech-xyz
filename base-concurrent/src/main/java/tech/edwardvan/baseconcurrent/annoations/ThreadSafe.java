package tech.edwardvan.baseconcurrent.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记 线程安全 的类或者写法
 * <p>
 * 线程安全定义:
 * 不管业务中遇到怎样的多个线程访问某对象或某方法的情况
 * 而在编程这个业务逻辑的时候,都不需要额外做任何额外的处理(也就是可以像单线程编程一样)
 * 程序也可以正常运行(不会因为多线程而出错),就可以称为线程安全
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default "";
}
