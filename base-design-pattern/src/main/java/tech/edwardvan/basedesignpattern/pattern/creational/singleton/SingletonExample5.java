package tech.edwardvan.basedesignpattern.pattern.creational.singleton;


/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * <p>
 * 线程安全
 *
 * @author EdwardVan
 */
public class SingletonExample5 {

    /**
     * 私有构造函数
     */
    private SingletonExample5() {

    }

    /**
     * 单例对象 volatile + 双重检测机制 -> 禁止指令重排
     */
    private volatile static SingletonExample5 instance = null;

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample5 getInstance() {
        // 双重检测机制
        if (instance == null) {
            // 同步锁
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
