package tech.edwardvan.basedesignpattern.principle.singleresponsibility;

/**
 * 单一职责原则
 *
 * 类职责单一
 *
 * @author EdwardVan
 */
public class SingleResponsibilityExample2 {
    public static void main(String[] args) {
        Terrestrial terrestrial = new Terrestrial();
        terrestrial.breathe("牛");
        terrestrial.breathe("羊");
        terrestrial.breathe("猪");

        Aquatic aquatic = new Aquatic();
        aquatic.breathe("鱼");
    }

    /**
     * 陆栖动物
     */
    public static class Terrestrial {
        public void breathe(String animal) {
            System.out.println(animal + "呼吸空气");
        }
    }

    /**
     * 水栖动物
     */
    public static class Aquatic {
        public void breathe(String animal) {
            System.out.println(animal + "呼吸水");
        }
    }


}
