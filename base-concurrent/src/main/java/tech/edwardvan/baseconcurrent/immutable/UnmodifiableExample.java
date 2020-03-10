package tech.edwardvan.baseconcurrent.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiableXXX的不可变性
 *
 * @author EdwardVan
 */
@Slf4j
public class UnmodifiableExample {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //直接抛出异常UnsupportedOperationException
        map.put(1, 3);
        log.info("map key:1 , value:{}", map.get(1));
    }

}