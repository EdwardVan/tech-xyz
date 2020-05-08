package tech.edwardvan.basejava.nested;

import lombok.extern.slf4j.Slf4j;

/**
 * lambda 表达式
 */
@Slf4j
public class LambdaExample {

    private static int i = 1;
    private int j = 10;

    public void f() {

        int k = 20;

        new Thread(() -> {
            log.info("i:{}", i);
            log.info("j:{}", j);
            log.info("k:{}", k);
        }).start();
    }

    public static void main(String[] args) {
        new LambdaExample().f();
    }
}

