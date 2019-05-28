package tech.edwardvan.basedesignpattern.pattern.structural.decorator;

/**
 * 装饰者模式
 *
 * @author EdwardVan
 */
public class DecoratorExample2 {

    public static void main(String[] args) {
        ICake battercakeWithEggSausage = new SausageDecorator(new EggDecorator(new Battercake()));
        System.out.println(battercakeWithEggSausage.getDesc());
        System.out.println(battercakeWithEggSausage.cost());
        ICake sesameCakeWithEggSausage = new EggDecorator(new SausageDecorator(new SesameCake()));
        System.out.println(sesameCakeWithEggSausage.getDesc());
        System.out.println(sesameCakeWithEggSausage.cost());
    }

    /**
     * 煎饼抽象接口
     */
    public interface ICake {
        /**
         * 煎饼描述
         */
        String getDesc();

        /**
         * 价格
         */
        int cost();
    }

    /**
     * 煎饼
     */
    public static class Battercake implements ICake {

        @Override
        public String getDesc() {
            return "煎饼";
        }

        @Override
        public int cost() {
            return 8;
        }
    }

    /**
     * 芝麻饼
     */
    public static class SesameCake implements ICake {

        @Override
        public String getDesc() {
            return "芝麻饼";
        }

        @Override
        public int cost() {
            return 10;
        }
    }

    /**
     * 抽象装饰者
     */
    public abstract static class AbstractDecorator implements ICake {
        ICake cake;

        public AbstractDecorator(ICake cake) {
            this.cake = cake;
        }

        @Override
        public String getDesc() {
            return cake.getDesc();
        }

        @Override
        public int cost() {
            return cake.cost();
        }
    }

    /**
     * 鸡蛋装饰者
     */
    public static class EggDecorator extends AbstractDecorator {

        public EggDecorator(ICake cake) {
            super(cake);
        }

        @Override
        public String getDesc() {
            return cake.getDesc() + "加一颗鸡蛋";
        }

        @Override
        public int cost() {
            return cake.cost() + 1;
        }
    }

    /**
     * 香肠装饰者
     */
    public static class SausageDecorator extends AbstractDecorator {

        public SausageDecorator(ICake cake) {
            super(cake);
        }

        @Override
        public String getDesc() {
            return cake.getDesc() + "加一根香肠";
        }

        @Override
        public int cost() {
            return cake.cost() + 2;
        }
    }

}
