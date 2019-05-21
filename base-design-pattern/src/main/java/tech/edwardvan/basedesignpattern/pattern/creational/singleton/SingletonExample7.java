package tech.edwardvan.basedesignpattern.pattern.creational.singleton;

/**
 * 枚举模式:最安全
 * <p>
 * 线程安全
 *
 * @author EdwardVan
 */
public class SingletonExample7 {

    /**
     * 私有构造函数
     */
    private SingletonExample7() {

    }

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        /**
         * JVM保证这个方法绝对只调用一次
         */
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}