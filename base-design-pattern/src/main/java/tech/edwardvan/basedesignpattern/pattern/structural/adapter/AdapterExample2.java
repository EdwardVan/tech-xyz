package tech.edwardvan.basedesignpattern.pattern.structural.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 适配器模式
 * <p>
 * 对象适配器(强调组合)
 *
 * @author EdwardVan
 */
@Slf4j
public class AdapterExample2 {

    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }

    /**
     * 被适配者
     */
    public static class Adaptee {
        public void adapteeRequest() {
            log.info("被适配者的方法");
        }
    }

    /**
     * 目标接口
     */
    public interface Target {
        void request();
    }

    /**
     * 适配器
     */
    public static class Adapter implements Target {

        Adaptee adaptee = new Adaptee();

        @Override
        public void request() {
            log.info("do something");
            adaptee.adapteeRequest();
            log.info("do something");
        }
    }

}
