package tech.edwardvan.basedesignpattern.pattern.creational.singleton;


/**
 * 单例模式
 * <p>
 * 优点:
 * 提供了对唯一实例的受控访问.因为单例类封装了它的唯一实例,所以它可以严格控制客户怎样以及何时访问它,并为设计及开发团队提供了共享的概念.
 * 由于在系统内存中只存在一个对象,因此可以节约系统资源,对于一些需要频繁创建和销毁的对象,单例模式无疑可以提高系统的性能.
 * 允许可变数目的实例.我们可以基于单例模式进行扩展,使用与单例控制相似的方法来获得指定个数的对象实例.
 * 缺点:
 * 由于单例模式中没有抽象层,因此单例类的扩展有很大的困难.
 * 单例类的职责过重,在一定程度上违背了"单一职责原则".因为单例类既充当了工厂角色,提供了工厂方法,同时又充当了产品角色,包含一些业务方法,将产品的创建和产品的本身的功能融合到一起.
 * <p>
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * <p>
 * 线程不安全
 * <p>
 * 举例:
 * {@link Runtime}
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
