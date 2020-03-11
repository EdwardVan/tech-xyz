package tech.edwardvan.basedesignpattern.pattern.creational.simplefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单工厂模式
 *
 * @author EdwardVan
 */
@Slf4j
public class SimpleFactoryExample2 {
    public static void main(String[] args) {
        //方法中只引入VideoFactory类
        Video javaVideo = VideoFactory.getVideo("java");
        javaVideo.produce();
        Video pythonVideo = VideoFactory.getVideo("python");
        pythonVideo.produce();
    }

    /**
     * 视频工厂
     * 工厂类中引入JavaVideo和PythonVideo类
     * 如果要添加新的课程,则需要修改本类,修改会带来风险,不符合开闭原则
     */
    public static class VideoFactory {
        public static Video getVideo(String type) {
            if ("java".equalsIgnoreCase(type)) {
                return new JavaVideo();
            } else if ("python".equalsIgnoreCase(type)) {
                return new PythonVideo();
            }
            return null;
        }
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
