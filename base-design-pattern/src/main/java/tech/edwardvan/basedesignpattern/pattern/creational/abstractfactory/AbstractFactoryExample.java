package tech.edwardvan.basedesignpattern.pattern.creational.abstractfactory;

import lombok.extern.slf4j.Slf4j;

import java.sql.Statement;

/**
 * 抽象工厂模式
 * <p>
 * 优点:
 * 当一个产品族中的多个对象被设计成一起工作时,它能够保证客户端始终只使用同一个产品族中的对象.这对一些需要根据当前环境来决定其行为的软件系统来说,是一种非常实用的设计模式.
 * 增加新的具体工厂和产品族很方便,无须修改已有系统,符合"开闭原则".
 * 缺点:
 * 在添加新的产品对象时,难以扩展抽象工厂来生产新种类的产品,这是因为在抽象工厂角色中规定了所有可能被创建的产品集合,要支持新种类的产品就意味着要对该接口进行扩展,而这将涉及到对抽象工厂角色及其所有子类的修改,显然会带来较大的不便.
 * 开闭原则的倾斜性(增加新的工厂和产品族容易,增加新的产品等级结构麻烦).
 * 举例:
 * {@link Statement}
 * {@link org.apache.ibatis.session.SqlSessionFactory}
 *
 * @author EdwardVan
 */
@Slf4j
public class AbstractFactoryExample {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
        courseFactory = new PythonCourseFactory();
        video = courseFactory.getVideo();
        article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }

    /**
     * java课程工厂
     */
    public static class JavaCourseFactory implements CourseFactory {
        @Override
        public Video getVideo() {
            return new JavaVideo();
        }

        @Override
        public Article getArticle() {
            return new JavaArticle();
        }
    }

    /**
     * python课程工厂
     */
    public static class PythonCourseFactory implements CourseFactory {
        @Override
        public Video getVideo() {
            return new PythonVideo();
        }

        @Override
        public Article getArticle() {
            return new PythonArticle();
        }
    }

    /**
     * 课程工厂接口
     */
    public interface CourseFactory {
        /**
         * 获取视频
         */
        Video getVideo();

        /**
         * 获取笔记
         */
        Article getArticle();
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


    /**
     * 视频抽象类
     */
    public static abstract class Video {
        public abstract void produce();
    }

    /**
     * java笔记
     */
    public static class JavaArticle extends Article {
        @Override
        public void produce() {
            log.info("编写Java课程笔记");
        }
    }

    /**
     * python笔记
     */
    public static class PythonArticle extends Article {
        @Override
        public void produce() {
            log.info("编写Python课程笔记");
        }
    }

    /**
     * 笔记抽象类
     */
    public static abstract class Article {
        public abstract void produce();
    }
}
