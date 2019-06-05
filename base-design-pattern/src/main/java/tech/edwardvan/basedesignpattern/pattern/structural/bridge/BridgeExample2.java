package tech.edwardvan.basedesignpattern.pattern.structural.bridge;

/**
 * 桥接模式
 *
 * @author EdwardVan
 */
public class BridgeExample2 {

    public static void main(String[] args) {
        // 创建具体的实现对象
        MessageImplementor impl = new MessageSMS();
        //创建普通消息对象
        AbstractMessage message = new CommonMessage(impl);
        message.sendMessage("消息内容", "EdwardVan");

        //将实现方式切换成邮件，再次发送
        impl = new MessageEmail();
        //创建加急消息对象
        message = new UrgencyMessage(impl);
        message.sendMessage("消息内容", "EdwardVan");
    }

    /**
     * 消息处理抽象层
     */
    public abstract static class AbstractMessage {
        /**
         * 持有一个实现部分的对象
         */
        MessageImplementor impl;

        public AbstractMessage(MessageImplementor impl) {
            this.impl = impl;
        }

        /**
         * 发送消息,委派给实现部分的方法
         */
        public void sendMessage(String message, String toUser) {
            this.impl.send(message, toUser);
        }
    }

    /**
     * 普通消息发送
     */
    public static class CommonMessage extends AbstractMessage {

        public CommonMessage(MessageImplementor impl) {
            super(impl);
        }

        @Override
        public void sendMessage(String message, String toUser) {
            // 对于普通消息,直接调用父类方法,发送消息即可
            super.sendMessage(message, toUser);
        }
    }

    /**
     * 加急消息发送
     */
    public static class UrgencyMessage extends AbstractMessage {

        public UrgencyMessage(MessageImplementor impl) {
            super(impl);
        }

        @Override
        public void sendMessage(String message, String toUser) {
            message = "加急:" + message;
            super.sendMessage(message, toUser);
        }

        /**
         * 扩展自己的新功能,监控某消息的处理状态
         */
        public Object watch(String messageId) {
            // 根据消息id获取消息的状态,组织成监控的数据对象,然后返回
            return null;
        }
    }


    /**
     * 消息种类
     */
    public interface MessageImplementor {
        /**
         * 发送消息
         */
        void send(String message, String toUser);
    }

    /**
     * 系统内消息
     */
    public static class MessageSMS implements MessageImplementor {

        @Override
        public void send(String message, String toUser) {

            System.out.println("使用系统内短消息的方法,发送消息'" + message + "'给" + toUser);
        }
    }

    /**
     * 邮件消息
     */
    public static class MessageEmail implements MessageImplementor {

        @Override
        public void send(String message, String toUser) {
            System.out.println("使用邮件短消息的方法,发送消息'" + message + "'给" + toUser);
        }
    }

    /**
     * 手机消息
     */
    public static class MessageMobile implements MessageImplementor {

        @Override
        public void send(String message, String toUser) {
            System.out.println("使用手机的方法,发送消息'" + message + "'给" + toUser);
        }
    }
}
