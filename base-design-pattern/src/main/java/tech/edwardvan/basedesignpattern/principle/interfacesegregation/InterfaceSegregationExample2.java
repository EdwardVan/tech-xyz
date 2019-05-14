package tech.edwardvan.basedesignpattern.principle.interfacesegregation;

/**
 * 接口隔离原则
 *
 * @author EdwardVan
 */
public class InterfaceSegregationExample2 {
    /**
     * 动物吃饭行为接口
     */
    public interface IEatAnimalAction {
        void eat();
    }

    /**
     * 动物飞翔行为接口
     */
    public interface IFlyAnimalAction {
        void fly();
    }

    /**
     * 动物游泳行为接口
     */
    public interface ISwimAnimalAction {
        void swim();
    }

    /**
     * 狗
     */
    public static class Dog implements ISwimAnimalAction, IEatAnimalAction {

        @Override
        public void eat() {

        }

        @Override
        public void swim() {

        }
    }

    /**
     * 鸟
     */
    public class Bird implements IFlyAnimalAction, IEatAnimalAction {
        @Override
        public void eat() {

        }

        @Override
        public void fly() {

        }
    }
}
