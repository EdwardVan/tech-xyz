package tech.edwardvan.basedesignpattern.pattern.structural.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 适配器模式
 * <p>
 * 优点:
 * 将目标类和适配者类解耦,通过引入一个适配器类来重用现有的适配者类,而无须修改原有代码
 * 增加了类的透明性和复用性,将具体的实现封装在适配者类中,对于客户端类来说是透明的,而且提高了适配者的复用性
 * 缺点:
 * 增加系统复杂性。
 * <p>
 * 类适配器(强调继承)
 * <p>
 * 举例:
 * {@link org.apache.ibatis.logging.slf4j.Slf4jLoggerImpl}
 * {@link org.apache.ibatis.logging.log4j2.Log4j2Impl}
 *
 * @author EdwardVan
 */
@Slf4j
public class AdapterExample {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }

    /**
     * 被适配者
     * 已经存在的Adaptee类不能直接重用,因为它不匹配Target接口
     */
    public static class Adaptee {
        public void adapteeRequest() {
            log.info("被适配者的方法");
        }
    }

    /**
     * 目标接口
     * 客户端需要一个Target接口
     */
    public interface Target {
        void request();
    }

    /**
     * 适配器
     * 将Adaptee转换为实现Target接口的类
     */
    private static class Adapter extends Adaptee implements Target {
        @Override
        public void request() {
            log.info("do something");
            super.adapteeRequest();
            log.info("do something");
        }
    }
}
