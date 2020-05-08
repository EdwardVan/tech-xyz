package tech.edwardvan.basejava.nested;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * 函数式编程-方法引用
 */
@Slf4j
public class LambdaExample3 {

    public static void main(String[] args) {
        // 静态方法引用--通过类名调用
        Consumer<String> consumerStatic = Test::MyNameStatic;
        consumerStatic.accept("test1");

        //实例方法引用--通过实例调用
        Test test = new Test();
        Consumer<String> consumer = test::myName;
        consumer.accept("test2");

        // 构造方法方法引用--无参数
        Supplier<Test> supplier = Test::new;
        log.info(supplier.get().toString());
    }


    private static class Test {
        /**
         * 静态方法
         */
        public static void MyNameStatic(String name) {
            log.info(name);
        }

        /**
         * 实例方法
         */
        public void myName(String name) {
            log.info(name);
        }

        /**
         * 无参构造方法
         */
        public Test() {
        }
    }
}

