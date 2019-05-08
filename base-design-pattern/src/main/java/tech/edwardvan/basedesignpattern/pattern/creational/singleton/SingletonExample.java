package tech.edwardvan.basedesignpattern.pattern.creational.singleton;


/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 *
 * 线程不安全
 *
 * @author EdwardVan
 */
public class SingletonExample {

    /**
     * 私有构造函数
     */
    private SingletonExample() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample instance = null;

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }
}
