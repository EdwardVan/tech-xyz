package tech.edwardvan.basedesignpattern.pattern.creational.prototype;

import java.util.Date;

/**
 * 原型模式
 * <p>
 * 浅拷贝
 *
 * 举例:
 * {@link java.util.ArrayList}
 *
 *
 * @author EdwardVan
 */
public class PrototypeExample {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("P1", new Date(0));
        Person p2 = (Person) p1.clone();
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
    public static class Person implements Cloneable {
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
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
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
