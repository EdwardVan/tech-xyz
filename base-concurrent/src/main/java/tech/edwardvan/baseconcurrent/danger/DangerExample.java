package tech.edwardvan.baseconcurrent.danger;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import tech.edwardvan.baseconcurrent.annoations.NotThreadSafe;
import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.Arrays;

/**
 * 对象的不安全发布与溢出
 *
 * @author EdwardVan
 */
@Slf4j
public class DangerExample {

    public static void main(String[] args) throws InterruptedException {
        unSafePub();
        safePub();
        escape();
    }

    /**
     * 不安全发布
     */
    @NotThreadSafe
    public static void unSafePub() {

        class UnSafePub {
            private String[] states = {"a", "b", "c", "d"};

            // 发布出去
            public String[] getStates() {
                return states;
            }
        }

        UnSafePub unSafePub = new UnSafePub();
        log.info("Init array is: {}", Arrays.toString(unSafePub.getStates()));

        unSafePub.getStates()[0] = "f";
        log.info("After modify.the  array is: {}", Arrays.toString(unSafePub.getStates()));
    }

    /**
     * 安全发布
     * 用副本的方式解决
     */
    @ThreadSafe
    public static void safePub() {

        class SafePub {
            private String[] states = {"a", "b", "c", "d"};

            // 发布出去
            public String[] getStates() {
                String[] newStates = Arrays.copyOf(states, states.length);
                return newStates;
            }
        }

        SafePub safePub = new SafePub();
        log.info("Init array is: {}", Arrays.toString(safePub.getStates()));

        safePub.getStates()[0] = "f";
        log.info("After modify.the  array is: {}", Arrays.toString(safePub.getStates()));
    }

    /**
     * 对象溢出
     */
    @NotThreadSafe
    public static void escape() throws InterruptedException {
        Thread thread = new Thread(() -> {
            new Escape(1, 1);
        });
        thread.start();
        Thread.sleep(1000);
        log.info("escape对象是否为空:{}", escape == null);
        log.info("escape对象中中的x的值为:{},y的值为:{}", escape.getX(), escape.getY());
    }

    public static Escape escape;

    @Data
    @NotThreadSafe
    static class Escape {

        private int x;
        private int y;

        public Escape(int x, int y) {
            this.x = x;
            //这步操作导致其他线程会在当前对象构造完毕之前就已经看到
            DangerExample.escape = this;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.y = y;
            log.info("Escape对象初始化完成");
        }
    }


}
