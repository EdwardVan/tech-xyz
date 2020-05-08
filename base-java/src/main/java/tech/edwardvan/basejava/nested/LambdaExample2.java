package tech.edwardvan.basejava.nested;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式编程
 * <p>
 * {@link Function}:一个入参,一个返回值
 * {@link Consumer}:一个入参,无返回值
 * {@link Supplier}:无入参,有返回值
 * {@link Predicate}:一个入参,返回Boolean
 */
@Slf4j
public class LambdaExample2 {

    public static void main(String[] args) {
        Function<Integer, String> function = i -> Integer.toString(i + 5);
        log.info("function.apply():{}", function.apply(5));
        Consumer<String> consumer = s -> log.info("consumer.accept():{}", s);
        consumer.accept("hello");
        Supplier<String> supplier = () -> "hello2";
        log.info("supplier.get():{}", supplier.get());
        Predicate predicate = (c) -> true;
        log.info("predicate.test():{}", predicate.test(new Object()));
    }
}

