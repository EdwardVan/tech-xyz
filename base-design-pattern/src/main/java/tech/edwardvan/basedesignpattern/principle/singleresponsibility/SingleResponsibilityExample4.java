package tech.edwardvan.basedesignpattern.principle.singleresponsibility;

/**
 * 单一职责原则
 * <p>
 * 接口职责单一
 *
 * @author EdwardVan
 */
public class SingleResponsibilityExample4 {
    /**
     * 课程内容
     */
    public interface ICourseContent {
        /**
         * 课程名称
         */
        String getCourseName();

        /**
         * 课程视频字节流
         */
        byte[] getCourseVideo();
    }

    /**
     * 课程管理
     */
    public interface ICourseManager {
        /**
         * 购买课程
         */
        void buyCourse();

        /**
         * 退订课程
         */
        void refundCourse();
    }
}
