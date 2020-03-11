package tech.edwardvan.basedesignpattern.pattern.creational.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 工厂方法模式
 * <p>
 * 优点:
 * 在系统中加入新产品时,无须修改抽象工厂和抽象产品提供的接口,无须修改客户端,也无须修改其他的具体工厂和具体产品,而只要添加一个具体工厂和具体产品就可以了.
 * 系统的可扩展性也就变得非常好,完全符合"开闭原则".
 * 缺点:
 * 在添加新产品时,需要编写新的具体产品类,而且还要提供与之对应的具体工厂类,系统中类的个数将成对增加,在一定程度上增加了系统的复杂度,有更多的类需要编译和运行,会给系统带来一些额外的开销.
 *
 * @author EdwardVan
 */
@Slf4j
public class FactoryMethodExample {
    public static void main(String[] args) {
        VideoFactory javaVideoFactory = new JavaVideoFactory();
        javaVideoFactory.getVideo().produce();
        VideoFactory pythonVideoFactory = new PythonVideoFactory();
        pythonVideoFactory.getVideo().produce();
    }

    /**
     * java视频工厂
     */
    public static class JavaVideoFactory extends VideoFactory {
        @Override
        public Video getVideo() {
            return new JavaVideo();
        }
    }

    /**
     * Python视频工厂
     */
    public static class PythonVideoFactory extends VideoFactory {
        @Override
        public Video getVideo() {
            return new PythonVideo();
        }
    }

    /**
     * 抽象视频工厂
     */
    public static abstract class VideoFactory {
        public abstract Video getVideo();
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
