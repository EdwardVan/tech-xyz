package tech.edwardvan.basedesignpattern.pattern.structural.bridge;

/**
 * 桥接模式
 * 优点:
 * 分离抽象接口及其实现部分.
 * 桥接模式有时类似于多继承方案,但是多继承方案违背了类的单一职责原则(即一个类只有一个变化的原因),复用性比较差,而且多继承结构中类的个数非常庞大,桥接模式是比多继承方案更好的解决方法.
 * 桥接模式提高了系统的可扩充性,在两个变化维度中任意扩展一个维度,都不需要修改原有系统.
 * 缺点:
 * 桥接模式的引入会增加系统的理解与设计难度,由于聚合关联关系建立在抽象层,要求开发者针对抽象进
 * 举例:
 * {@link java.sql.DriverManager}
 *
 * @author EdwardVan
 */
public class BridgeExample {

    //采用通过继承来扩展的实现方式,有个明显的缺点,扩展消息的种类和消息处理很困难.

    /**
     * 消息接口
     */
    public interface Message {
        /**
         * 发送消息
         */
        void send(String message, String toUser);
    }

    /**
     * 普通消息-系统内消息
     */
    public static class CommonMessageSMS implements Message {

        @Override
        public void send(String message, String toUser) {
            System.out.println("使用系统内短消息的方法,发送消息'" + message + "'给" + toUser);
        }
    }

    /**
     * 普通消息-邮件
     */
    public static class CommonMessageEmail implements Message {

        @Override
        public void send(String message, String toUser) {
            System.out.println("使用邮件短消息的方法,发送消息'" + message + "'给" + toUser);
        }

    }

    /**
     * 加急消息接口
     */
    public interface UrgencyMessage extends Message {
        /**
         * 监控指定消息的处理过程
         */
        Object watch(String messageId);
    }

    /**
     * 加急消息-系统内消息
     */
    public static class UrgencyMessageSMS implements UrgencyMessage {

        @Override
        public Object watch(String messageId) {
            // 根据消息id获取消息的状态,组织成监控的数据对象,然后返回
            return null;
        }

        @Override
        public void send(String message, String toUser) {

            message = "加急:" + message;
            System.out.println("使用系统内短消息的方法,发送消息'" + message + "'给" + toUser);
        }

    }

    /**
     * 加急消息-邮件
     */
    public static class UrgencyMessageEmail implements UrgencyMessage {

        @Override
        public Object watch(String messageId) {
            // 根据消息id获取消息的状态,组织成监控的数据对象,然后返回
            return null;
        }

        @Override
        public void send(String message, String toUser) {
            message = "加急:" + message;
            System.out.println("使用邮件短消息的方法,发送消息'" + message + "'给" + toUser);
        }

    }
}
