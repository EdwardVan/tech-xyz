package tech.edwardvan.basedesignpattern.principle.singleresponsibility;

/**
 * 单一职责原则
 * <p>
 * 接口职责不单一
 *
 * @author EdwardVan
 */
public class SingleResponsibilityExample3 {

    /**
     * 课程接口
     */
    public interface ICourse {
        /**
         * 课程名称
         */
        String getCourseName();

        /**
         * 课程视频字节流
         */
        byte[] getCourseVideo();

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
