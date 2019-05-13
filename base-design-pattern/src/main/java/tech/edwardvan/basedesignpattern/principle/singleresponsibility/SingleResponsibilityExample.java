package tech.edwardvan.basedesignpattern.principle.singleresponsibility;

/**
 * 单一职责原则
 * <p>
 * 定义:不要存在多于一个导致类变更的原因.通俗的说,即一个类只负责一项职责.
 * 问题由来:类T负责两个不同的职责:职责P1,职责P2.当由于职责P1需求发生改变而需要修改类T时,有可能会导致原本运行正常的职责P2功能发生故障.
 * 解决方案:遵循单一职责原则.分别建立两个类T1 & T2,使T1完成职责P1功能,T2完成职责P2功能.这样,当修改类T1时,不会使职责P2发生故障风险;同理,当修改T2时,也不会使职责P1发生故障风险.
 *
 * 类职责不单一
 *
 * @author EdwardVan
 */
public class SingleResponsibilityExample {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.breathe("牛");
        animal.breathe("羊");
        animal.breathe("猪");
        animal.breathe("鱼");
    }

    /**
     * 动物类
     * 当添加动物种类时需要改变Animal类
     */
    public static class Animal {
        public void breathe(String animal) {
            if ("鱼".equals(animal)) {
                System.out.println(animal + "呼吸水");
            } else {
                System.out.println(animal + "呼吸空气");
            }
        }
    }
}
