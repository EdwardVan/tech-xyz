package tech.edwardvan.basedesignpattern.principle.liskovsubstitution;

/**
 * 里氏替换原则
 * <p>
 * 定义1:如果对每一个类型为 T1的对象 o1,都有类型为 T2 的对象o2,使得以 T1定义的所有程序 P 在所有的对象 o1 都代换成 o2 时,程序 P 的行为没有发生变化,那么类型 T2 是类型 T1 的子类型.
 * 定义2:所有引用基类的地方必须能透明地使用其子类的对象.
 * 问题由来:有一功能P1,由类A完成.现需要将功能P1进行扩展,扩展后的功能为P,其中P由原有功能P1与新功能P2组成.新功能P由类A的子类B来完成,则子类B在完成新功能P2的同时,有可能会导致原有功能P1发生故障.
 * 解决方案:当使用继承时,遵循里氏替换原则.类B继承类A时,除添加新的方法完成新增功能P2外,尽量不要重写父类A的方法,也尽量不要重载父类A的方法.
 * <p>
 * 总结:
 * 子类可以实现父类的抽象方法,但不能覆盖父类的非抽象方法.
 * 子类中可以增加自己特有的方法.
 * 当子类的方法重载父类的方法时,方法的前置条件(即方法的形参)要比父类方法的输入参数更宽松.
 * 当子类的方法实现父类的抽象方法时,方法的后置条件(即方法的返回值)要比父类更严格.
 *
 * @author EdwardVan
 */
public class LiskovSubstitutionExample {

    public static void main(String[] args) {
        JavaDiscountCourse javaDiscountCourse = new JavaDiscountCourse(1, "Java教学课程", 100d);
        System.out.println(javaDiscountCourse.getPrice());
    }

    /**
     * Java课程类
     */
    public static class JavaCourse {
        private Integer Id;
        private String name;
        private Double price;

        public JavaCourse(Integer id, String name, Double price) {
            this.Id = id;
            this.name = name;
            this.price = price;
        }

        public Integer getId() {
            return this.Id;
        }

        public String getName() {
            return this.name;
        }

        public Double getPrice() {
            return this.price;
        }

    }

    /**
     * Java优惠课程类
     * 当有需求为获取优惠价格时,不在原有类的基础上修改,而是继承子类并添加方法
     * 但是直接重写getPrice方法违反了里氏替换原则
     */
    public static class JavaDiscountCourse extends JavaCourse {

        public JavaDiscountCourse(Integer id, String name, Double price) {
            super(id, name, price);
        }

        /**
         * 课程优惠价格
         */
        @Override
        public Double getPrice() {
            return super.getPrice() * 0.8;
        }

    }
}
