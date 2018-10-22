package tech.edwardvan.baseconcurrent.immutable;

import com.google.common.collect.Maps;
import tech.edwardvan.baseconcurrent.annoations.NotThreadSafe;

import java.util.Map;

/**
 * final的不可变性
 */
@NotThreadSafe
public class ImmutableExample {

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
        System.out.println("map key:1 , value:" + map.get(1));
    }

    private void f(final int a) {
        //a = 1;
    }
}
