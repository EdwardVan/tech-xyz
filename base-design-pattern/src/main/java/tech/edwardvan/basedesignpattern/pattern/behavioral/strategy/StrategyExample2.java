package tech.edwardvan.basedesignpattern.pattern.behavioral.strategy;

/**
 * 策略模式
 *
 * @author EdwardVan
 */
public class StrategyExample2 {

    public static void main(String[] args) {
        //使用策略模式
        Context context = new Context(new AWM());
        context.gun();
        context.setWeapon(new S12K());
        context.gun();
    }

    /**
     * 武器接口
     */
    public interface Weapon {
        public void gun();
    }

    /**
     *
     */
    public static class AWM implements Weapon {

        @Override
        public void gun() {
            System.out.println("使用AWM狙击步枪");
        }

    }

    /**
     * S12K
     */
    public static class S12K implements Weapon {

        @Override
        public void gun() {
            System.out.println("使用S12K霰弹枪");
        }

    }

    /**
     * 环境类
     */
    public static class Context {

        Weapon weapon;

        public Context(Weapon weapon) {
            this.weapon = weapon;
        }

        /**
         * 切换枪
         */
        public void setWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        /**
         * 射击
         */
        public void gun() {
            weapon.gun();
        }
    }
}
