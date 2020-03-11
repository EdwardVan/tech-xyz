package tech.edwardvan.basedesignpattern.pattern.creational.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 枚举模式:最安全
 * <p>
 * 线程安全
 * 推荐使用
 *
 * @author EdwardVan
 */
@Slf4j
public class SingletonExample7 {

    public static void main(String[] args) {
        SingletonExample7 instance = SingletonExample7.getInstance();
    }

    /**
     * 私有构造函数
     */
    private SingletonExample7() {

    }

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample7 getInstance() {
        //在使用的时候才会创建
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        /**
         * JVM保证这个方法绝对只调用一次
         */
        Singleton() {
            log.info("Singleton枚举构造方法");
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
