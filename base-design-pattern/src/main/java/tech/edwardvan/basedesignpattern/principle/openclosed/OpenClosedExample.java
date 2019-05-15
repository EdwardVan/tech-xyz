package tech.edwardvan.basedesignpattern.principle.openclosed;

/**
 * 开放封闭原则
 * <p>
 * 定义:一个软件实体如类 & 模块和函数应该对扩展开放,对修改关闭.
 * 问题由来:在软件的生命周期内,因为变化 & 升级 & 维护等原因需要对软件原有代码进行修改时,可能会给旧代码中引入错误,也可能会使我们不得不对整个功能进行重构,并且需要原有代码经过重新测试.
 * 解决方案:当软件需要变化时,尽量通过扩展软件实体的行为来实现变化,而不是通过修改已有的代码来实现变化.
 *
 * 总结:
 * 单一职责原则告诉我们实现类要职责单一
 * 里氏替换原则告诉我们不要破坏继承体系
 * 依赖倒置原则告诉我们要面向接口编程
 * 接口隔离原则告诉我们在设计接口的时候要精简单一
 * 最少知识原则告诉我们要降低耦合
 * 开闭原则是总纲,告诉我们要对扩展开放,对修改关闭
 *
 * @author EdwardVan
 */
public class OpenClosedExample {
    public static void main(String[] args) {
        ICourse iCourse = new JavaDiscountCourse(1, "Java教学课程", 100d);
        JavaDiscountCourse javaCourse = (JavaDiscountCourse) iCourse;
        System.out.println("课程ID:" + javaCourse.getId() + " 课程名称:" + javaCourse.getName() + " 课程原价:" + javaCourse.getPrice() + " 课程折后价格:" + javaCourse.getDiscountPrice() + "元");
    }

    /**
     * 课程接口
     */
    public interface ICourse {
        /**
         * 课程id
         */
        Integer getId();

        /**
         * 课程名称
         */
        String getName();

        /**
         * 课程价格
         */
        Double getPrice();
    }

    /**
     * Java课程类
     */
    public static class JavaCourse implements ICourse {
        private Integer Id;
        private String name;
        private Double price;

        public JavaCourse(Integer id, String name, Double price) {
            this.Id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public Integer getId() {
            return this.Id;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Double getPrice() {
            return this.price;
        }

    }

    /**
     * 当有需求为获取优惠价格时,不在原有类的基础上修改,而是继承子类并添加方法
     * Java优惠课程类
     */
    public static class JavaDiscountCourse extends JavaCourse {

        public JavaDiscountCourse(Integer id, String name, Double price) {
            super(id, name, price);
        }

        /**
         * 课程优惠价格
         */
        public Double getDiscountPrice() {
            return super.getPrice() * 0.8;
        }

    }

}
