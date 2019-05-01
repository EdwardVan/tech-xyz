package tech.edwardvan.basedesignpattern.singleton;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * <p>
 * 线程安全
 * 缺点:效率低,每次获取实例都要加锁
 *
 * @author EdwardVan
 */
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 获取实例的静态方法
     */
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
