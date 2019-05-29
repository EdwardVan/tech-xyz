package tech.edwardvan.basedesignpattern.pattern.structural.flyweight;

/**
 * 享元模式
 * <p>
 * 面试题
 *
 * @author EdwardVan
 */
public class FlyweightExample2 {

    public static void main(String[] args) {

        Integer a = Integer.valueOf(100);
        Integer b = 100;

        Integer c = Integer.valueOf(1000);
        Integer d = 1000;

        System.out.println("a==b:" + (a == b));

        System.out.println("c==d:" + (c == d));

    }
}
