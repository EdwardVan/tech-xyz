package tech.edwardvan.basedesignpattern.pattern.creational.singleton;

import java.io.*;

/**
 * 解决序列化情况下的单例模式
 * <p>
 * 线程安全
 *
 * @author EdwardVan
 */
public class SingletonExample9 implements Serializable, Cloneable {

    public static void main(String[] args) throws Exception {
        String filePath = "d:/test.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(instance);
        File file = new File(filePath);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        SingletonExample9 newInstance = (SingletonExample9) ois.readObject();
        System.out.println(instance);
        System.out.println(newInstance);
    }

    /**
     * 私有构造函数
     */
    private SingletonExample9() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample9 instance = new SingletonExample9();

    /**
     * 获取实例的静态方法
     */
    public static SingletonExample9 getInstance() {
        return instance;
    }

    /**
     * 反序列化时会判断类中是否有该方法,如果有则调用,如果没有则反射创建
     */
    private Object readResolve() {
        return instance;
    }

    @Override
    protected Object clone() {
        return getInstance();
    }
}
