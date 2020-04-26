package tech.edwardvan.basedesignpattern.pattern.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 策略模式
 * 举例:
 * {@link java.util.Comparator}
 *
 * @author EdwardVan
 */
@Slf4j
public class StrategyExample {

    public static void main(String[] args) {
        //未使用策略模式
        Context context = new Context();
        context.gun();
        context.setWeaponName("AWM");
        context.gun();
        context.setWeaponName("S12K");
        context.gun();
    }

    /**
     * 环境类
     */
    public static class Context {

        String weaponName;

        /**
         * 切换枪
         */
        public void setWeaponName(String weaponName) {
            this.weaponName = weaponName;
        }

        /**
         * 射击
         */
        public void gun() {
            if ("AWM".equals(weaponName)) {
                log.info("使用AWM狙击步枪");
            } else if ("S12K".equals(weaponName)) {
                log.info("使用S12K霰弹枪");
            } else {
                log.info("请设置武器");
            }
        }
    }
}
