package tech.edwardvan.basedesignpattern.pattern.creational.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态内部类实现
 * <p>
 * 线程安全
 * <p>
 * 一定会发生类的初始化(加载)事件:
 * 1.new一个类的对象.
 * 2.使用类的静态成员(除了final常量)和静态方法.
 * 3.使用java.lang.reflect包的方法对类进行反射调用,例如:Class.forName("xxx").
 * 4.虚拟机启动,就是启动main方法所在类.
 * 5.当初始化一个类,如果其父类没有被初始化,则会先初始化它的父类.
 *
 * @author EdwardVan
 */
@Slf4j
public class SingletonExample8 {

    public static void main(String[] args) {
        SingletonExample8.print();
        SingletonExample8.getInstance();
    }

    /**
     * 静态代码块
     */
    static {
        log.info("SingletonExample8类静态代码块");
    }

    /**
     * 私有构造函数
     */
    private SingletonExample8() {
        // 防止反射入侵外部类
        if (SingletonHolder.instance != null) {
            throw new IllegalStateException();
        }
    }

    public static void print() {
        log.info("测试只调用外部类方法时,内部类是否加载");
    }

    /**
     * 调用时才会加载SingletonHolder类
     */
    public static SingletonExample8 getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 静态内部类
     * 只有在使用时才初始化类,并且底层保证类只初始化一次
     */
    private static class SingletonHolder {
        private static SingletonExample8 instance = new SingletonExample8();

        /**
         * 内部类静态代码块
         */
        static {
            log.info("SingletonHolder内部类静态代码块");
        }
    }
}
