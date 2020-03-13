package tech.edwardvan.basedesignpattern.pattern.structural.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 装饰者模式
 *
 * @author EdwardVan
 */
@Slf4j
public class DecoratorExample2 {

    public static void main(String[] args) {
        ICake battercakeWithEggSausage = new AddSausageCake(new AddEggCake(new Battercake()));
        log.info("{}的价格为:{}", battercakeWithEggSausage.getDesc(), battercakeWithEggSausage.cost());
        ICake sesameCakeWithEggSausage = new AddEggCake(new AddSausageCake(new SesameCake()));
        log.info("{}的价格为:{}", sesameCakeWithEggSausage.getDesc(), sesameCakeWithEggSausage.cost());
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
    public static abstract class AbstractCakeDecorator implements ICake {
        ICake cake;

        public AbstractCakeDecorator(ICake cake) {
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
    public static class AddEggCake extends AbstractCakeDecorator {

        public AddEggCake(ICake cake) {
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
    public static class AddSausageCake extends AbstractCakeDecorator {

        public AddSausageCake(ICake cake) {
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
