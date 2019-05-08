package tech.edwardvan.basedesignpattern.pattern.creational.singleton;


/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * <p>
 * 线程安全
 *
 * @author EdwardVan
 */
public class SingletonExample6 {

    /**
     * 私有构造函数
     */
    private SingletonExample6() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample6 instance = null;

    /**
     * 初始化静态方法
     */
    static {
        instance = new SingletonExample6();
    }

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
