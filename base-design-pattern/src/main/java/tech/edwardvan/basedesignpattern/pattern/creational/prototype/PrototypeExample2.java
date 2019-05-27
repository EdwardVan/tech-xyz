package tech.edwardvan.basedesignpattern.pattern.creational.prototype;

import java.io.*;
import java.util.Date;

/**
 * 原型模式
 * <p>
 * 深拷贝
 *
 * @author EdwardVan
 */
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

        System.out.println(p1);
        System.out.println(p2);
        p1.getBirthday().setTime(System.currentTimeMillis());
        System.out.println(p1);
        System.out.println(p2);
    }

    /**
     * 人类
     */
    public static class Person implements Serializable {
        /**
         * 姓名
         */
        private String name;
        /**
         * 生日
         */
        private Date birthday;

        public Person(String name, Date birthday) {
            this.name = name;
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", birthday=" + birthday +
                    '}';
        }
    }
}
