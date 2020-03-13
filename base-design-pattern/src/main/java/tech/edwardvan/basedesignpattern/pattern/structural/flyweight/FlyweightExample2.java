package tech.edwardvan.basedesignpattern.pattern.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 享元模式
 * <p>
 * 面试题
 *
 * @author EdwardVan
 */
@Slf4j
public class FlyweightExample2 {

    public static void main(String[] args) {

        Integer a = Integer.valueOf(100);
        Integer b = 100;

        Integer c = Integer.valueOf(1000);
        Integer d = 1000;

        log.info("a==b:{}", a == b);

        log.info("c==d:{}", c == d);

    }
}
