package tech.edwardvan.basedesignpattern.pattern.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Date;

/**
 * 原型模式
 * <p>
 * 深拷贝
 *
 * @author EdwardVan
 */
@Slf4j
public class PrototypeExample2 {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("P1", new Date(0));

        //将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(p1);
        //将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Person p2 = (Person) ois.readObject();

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
    public static class Person implements Serializable {
        /**
         * 姓名
         */
        private String name;
        /**
         * 生日
         */
        private Date birthday;
    }
}
