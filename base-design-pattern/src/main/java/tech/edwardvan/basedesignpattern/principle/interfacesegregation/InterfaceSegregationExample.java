package tech.edwardvan.basedesignpattern.principle.interfacesegregation;

/**
 * 接口隔离原则
 * <p>
 * 定义:客户端不应该依赖它不需要的接口;一个类对另一个类的依赖应该建立在最小的接口上.
 * 问题由来:类A通过接口I依赖类B,类C通过接口I依赖类D,如果接口I对于类A和类B来说不是最小接口,则类B和类D必须去实现他们不需要的方法.
 * 解决方案:将臃肿的接口I拆分为独立的几个接口,类A和类C分别与他们需要的接口建立依赖关系.也就是采用接口隔离原则.
 *
 * @author EdwardVan
 */
public class InterfaceSegregationExample {

    /**
     * 动物行为接口
     */
    public interface IAnimalAction {
        void eat();

        void fly();

        void swim();
    }

    /**
     * 狗
     * 不需要实现fly方法
     */
    public static class Dog implements IAnimalAction {

        @Override
        public void eat() {

        }

        @Override
        public void fly() {

        }

        @Override
        public void swim() {

        }
    }

    /**
     * 鸟
     * 不需要实现swim方法
     */
    public static class Bird implements IAnimalAction {
        @Override
        public void eat() {

        }

        @Override
        public void fly() {

        }

        @Override
        public void swim() {

        }
    }
}
