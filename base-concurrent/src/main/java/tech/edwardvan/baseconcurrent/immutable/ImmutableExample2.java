package tech.edwardvan.baseconcurrent.immutable;

import com.google.common.collect.Maps;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiableXXX的不可变性
 */
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
//        map.put(1, 3);
        System.out.println("map key:1 , value:" + map.get(1));
    }

}