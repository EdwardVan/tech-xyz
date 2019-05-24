package tech.edwardvan.basedesignpattern.pattern.creational.builder;

/**
 * 建造者模式
 * <p>
 * 优点:
 * 在建造者模式中,客户端不必知道产品内部组成的细节,将产品本身与产品的创建过程解耦,使得相同的创建过程可以创建不同的产品对象.
 * 每一个具体建造者都相对独立,而与其他的具体建造者无关,因此可以很方便地替换具体建造者或增加新的具体建造者,用户使用不同的具体建造者即可得到不同的产品对象.
 * 可以更加精细地控制产品的创建过程.将复杂产品的创建步骤分解在不同的方法中,使得创建过程更加清晰,也更方便使用程序来控制创建过程.
 * 增加新的具体建造者无须修改原有类库的代码,指挥者类针对抽象建造者类编程,系统扩展方便,符合"开闭原则".
 * 缺点:
 * 建造者模式所创建的产品一般具有较多的共同点,其组成部分相似,如果产品之间的差异性很大,则不适合使用建造者模式,因此其使用范围受到一定的限制.
 * 如果产品的内部变化复杂,可能会导致需要定义很多具体建造者类来实现这种变化,导致系统变得很庞大.
 * 举例:
 * java.lang.StringBuilder
 * com.google.common.collect.ImmutableSet
 * org.springframework.beans.factory.support.BeanDefinitionBuilder
 *
 * @author EdwardVan
 */
public class BuilderExample {
    public static void main(String[] args) {
        Course course = new Course.CourseBuilder().name("java课程").video("java课程视频").article("java课程笔记").build();
        System.out.println(course.toString());
    }

    /**
     * 课程类
     */
    public static class Course {
        /**
         * 课程名称
         */
        private String name;
        /**
         * 课程视频
         */
        private String video;
        /**
         * 课程笔记
         */
        private String article;

        public Course(String name, String video, String article) {
            this.name = name;
            this.video = video;
            this.article = article;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "name='" + name + '\'' +
                    ", video='" + video + '\'' +
                    ", article='" + article + '\'' +
                    '}';
        }

        public static class CourseBuilder {
            /**
             * 课程名称
             */
            private String name;
            /**
             * 课程视频
             */
            private String video;
            /**
             * 课程笔记
             */
            private String article;


            public CourseBuilder name(String name) {
                this.name = name;
                return this;
            }

            public CourseBuilder video(String video) {
                this.video = video;
                return this;
            }

            public CourseBuilder article(String article) {
                this.article = article;
                return this;
            }

            public Course build() {
                return new Course(name, video, article);
            }

        }
    }
}
