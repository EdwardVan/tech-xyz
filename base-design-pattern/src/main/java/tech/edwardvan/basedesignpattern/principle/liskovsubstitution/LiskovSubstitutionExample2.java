package tech.edwardvan.basedesignpattern.principle.liskovsubstitution;

/**
 * 里氏替换原则
 *
 * @author EdwardVan
 */
public class LiskovSubstitutionExample2 {

    public static void main(String[] args) {
        JavaDiscountCourse javaDiscountCourse = new JavaDiscountCourse(1, "Java教学课程", 100d);
        System.out.println(javaDiscountCourse.getPrice());
        System.out.println(javaDiscountCourse.getDiscountPrice());
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
     * 添加新的方法完成新增功能外,尽量不要重写父类的方法
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
