package tech.edwardvan.basedesignpattern.pattern.creational.simplefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单工厂模式
 * <p>
 * 优点:
 * 将工厂类含有必要的判断逻辑,可以决定在什么时候创建哪一个产品类的实例,客户端可以免除直接创建产品对象的责任,而仅仅"消费"产品;简单工厂模式通过这种做法实现了对责任的分割,它提供了专门的工厂类用于创建对象.
 * 客户端无须知道所创建的具体产品类的类名,只需要知道具体产品类所对应的参数即可,对于一些复杂的类名,通过简单工厂模式可以减少使用者的记忆量.
 * 通过引入配置文件,可以在不修改任何客户端代码的情况下更换和增加新的具体产品类,在一定程度上提高了系统的灵活性.
 * 缺点:
 * 由于工厂类集中了所有产品创建逻辑,一旦不能正常工作,整个系统都要受到影响.
 * 使用简单工厂模式将会增加系统中类的个数,在一定程序上增加了系统的复杂度和理解难度.
 * 系统扩展困难,一旦添加新产品就不得不修改工厂逻辑,在产品类型较多时,有可能造成工厂逻辑过于复杂,不利于系统的扩展和维护.
 * 简单工厂模式由于使用了静态工厂方法,造成工厂角色无法形成基于继承的等级结构.
 *
 * @author EdwardVan
 */
@Slf4j
public class SimpleFactoryExample {
    public static void main(String[] args) {
        //方法中直接引入JavaVideo和PythonVideo类
        Video javaVideo = new JavaVideo();
        javaVideo.produce();
        Video pythonVideo = new PythonVideo();
        pythonVideo.produce();
    }

    /**
     * 视频抽象类
     */
    public abstract static class Video {
        /**
         * 录制视频
         */
        public abstract void produce();
    }

    /**
     * java视频
     */
    public static class JavaVideo extends Video {
        @Override
        public void produce() {
            log.info("录制Java课程视频");
        }
    }

    /**
     * python视频
     */
    public static class PythonVideo extends Video {
        @Override
        public void produce() {
            log.info("录制Python课程视频");
        }
    }
}
