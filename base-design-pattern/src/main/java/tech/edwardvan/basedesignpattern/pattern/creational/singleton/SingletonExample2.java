package tech.edwardvan.basedesignpattern.pattern.creational.singleton;


/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * <p>
 * 线程安全
 * 优点:没有加锁,执行效率会提高.
 * 缺点:类加载时就初始化,浪费内存.
 *
 * @author EdwardVan
 */
public class SingletonExample2 {

    /**
     * 私有构造函数
     */
    private SingletonExample2() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
