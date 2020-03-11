package tech.edwardvan.basedesignpattern.pattern.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 原型模式
 * <p>
 * 浅拷贝
 * <p>
 * 举例:
 * {@link java.util.ArrayList}
 *
 * @author EdwardVan
 */
@Slf4j
public class PrototypeExample {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("P1", new Date(0));
        Person p2 = (Person) p1.clone();
        p2.setName("P2");
        log.info(p1.toString());
        log.info(p2.toString());
        p1.getBirthday().setTime(System.currentTimeMillis());
        log.info(p1.toString());
        log.info(p2.toString());
    }

    /**
     * 人类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person implements Cloneable {
        /**
         * 姓名
         */
        private String name;
        /**
         * 生日
         */
        private Date birthday;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
