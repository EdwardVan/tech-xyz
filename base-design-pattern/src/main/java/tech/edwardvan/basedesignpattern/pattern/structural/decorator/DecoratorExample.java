package tech.edwardvan.basedesignpattern.pattern.structural.decorator;

/**
 * 装饰者模式
 * 优点:
 * 装饰模式与继承关系的目的都是要扩展对象的功能,但是装饰模式可以提供比继承更多的灵活性.
 * 通过使用不同的具体装饰类以及这些装饰类的排列组合,可以创造出很多不同行为的组合.可以使用多个具体装饰类来装饰同一对象,得到功能更为强大的对象.
 * 具体构件类与具体装饰类可以独立变化,用户可以根据需要增加新的具体构件类和具体装饰类,在使用时再对其进行组合,原有代码无须改变,符合"开闭原则"
 * 缺点:
 * 使用装饰模式进行系统设计时将产生很多小对象,这些对象的区别在于它们之间相互连接的方式有所不同,而不是它们的类或者属性值有所不同,同时还将产生很多具体装饰类.这些装饰类和小对象的产生将增加系统的复杂度,加大学习与理解的难度.
 * 这种比继承更加灵活机动的特性,也同时意味着装饰模式比继承更加易于出错,排错也很困难,对于多次装饰的对象,调试时寻找错误可能需要逐级排查,较为烦琐.
 * 举例:
 * {@link java.io.BufferedInputStream}
 *
 * @author EdwardVan
 */
public class DecoratorExample {

    public static void main(String[] args) {
        // 容易造成类爆炸
        BattercakeWithEggSausage battercakeWithEggSausage = new BattercakeWithEggSausage();
        System.out.println(battercakeWithEggSausage.getDesc());
        System.out.println(battercakeWithEggSausage.cost());
        BattercakeWithSausage battercakeWithSausage = new BattercakeWithSausage();
        System.out.println(battercakeWithSausage.getDesc());
        System.out.println(battercakeWithSausage.cost());
    }

    /**
     * 煎饼
     */
    public static class Battercake {
        protected String getDesc() {
            return "煎饼";
        }

        protected int cost() {
            return 8;
        }

    }

    /**
     * 煎饼+鸡蛋
     */
    public static class BattercakeWithEgg extends Battercake {
        @Override
        public String getDesc() {
            return super.getDesc() + " 加一颗鸡蛋";
        }

        @Override
        public int cost() {
            return super.cost() + 1;
        }
    }

    /**
     * 煎饼+鸡蛋+香肠
     */
    public static class BattercakeWithEggSausage extends BattercakeWithEgg {
        @Override
        public String getDesc() {
            return super.getDesc() + " 加一根香肠";
        }

        @Override
        public int cost() {
            return super.cost() + 2;
        }
    }

    /**
     * 煎饼+香肠
     */
    public static class BattercakeWithSausage extends Battercake {
        @Override
        public String getDesc() {
            return super.getDesc() + " 加一根香肠";
        }

        @Override
        public int cost() {
            return super.cost() + 2;
        }
    }
}
