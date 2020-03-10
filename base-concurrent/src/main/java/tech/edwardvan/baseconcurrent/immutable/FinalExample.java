package tech.edwardvan.baseconcurrent.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final的不可变性
 *
 * @author EdwardVan
 */
@Slf4j
public class FinalExample {
    /**
     * 成员变量用final修饰
     */
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //a = 2;
        //b = "3";
        //map = Maps.newHashMap();
        map.put(1, 3);
        log.info("map key:1 , value:{}", map.get(1));
    }

    /**
     * final修饰方法参数
     */
    private void f(final int a) {
        //a = 1;

        //final修饰的局部变量使用前必须赋值
        final int b;
        //int c = b;
    }

    /**
     * final修饰类不能被继承
     final class A extends String {
     }
     */
}
