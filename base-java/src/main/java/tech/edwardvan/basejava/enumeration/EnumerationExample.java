package tech.edwardvan.basejava.enumeration;

/**
 * 实现原理
 * 在使用关键字enum创建枚举类型并编译后,编译器会生成一个相关的类,这个类继承了java.lang.Enum类.
 */
public class EnumerationExample {
    public static void main(String[] args) {
        Day day = Day.MONDAY;
        System.out.println("日期中文描述:" + day.getDesc());
        System.out.println("日期数字描述:" + day.getOrder());
    }

    enum Day {
        MONDAY("星期一", 1),
        TUESDAY("星期二", 2),
        WEDNESDAY("星期三", 3),
        THURSDAY("星期四", 4),
        FRIDAY("星期五", 5),
        SATURDAY("星期六", 6),
        SUNDAY("星期日", 7);//要用分号结束

        //中文描述
        private String desc;

        //数字描述
        private int order;

        //私有构造,防止被外部调用
        private Day(String desc, int order) {
            this.desc = desc;
            this.order = order;
        }

        //定义方法,返回描述,与常规类的定义没区别
        public String getDesc() {
            return desc;
        }

        public int getOrder() {
            return order;
        }
    }

}
