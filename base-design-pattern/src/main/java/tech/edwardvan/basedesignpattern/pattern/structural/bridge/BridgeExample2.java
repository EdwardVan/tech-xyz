package tech.edwardvan.basedesignpattern.pattern.structural.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 桥接模式
 *
 * @author EdwardVan
 */
@Slf4j
public class BridgeExample2 {

    public static void main(String[] args) {
        // 创建具体的实现对象
        MessageSender sender = new SMSSender();
        //创建普通消息对象
        Message message = new CommonMessage(sender);
        message.sendMessage("回家吃饭啦", "EdwardVan");

        //将实现方式切换成邮件，再次发送
        sender = new EmailSender();
        //创建加急消息对象
        message = new UrgencyMessage(sender);
        message.sendMessage("回家吃饭啦", "EdwardVan");
    }

    /**
     * 消息接口
     */
    public interface Message {
        void sendMessage(String message, String toUser);
    }


    /**
     * 消息抽象类
     */
    public abstract static class AbstractMessage implements Message {
        /**
         * 持有一个实现部分的对象
         */
        MessageSender sender;

        public AbstractMessage(MessageSender sender) {
            this.sender = sender;
        }

        /**
         * 发送消息,委派给实现部分的方法
         */
        @Override
        public void sendMessage(String message, String toUser) {
            this.sender.send(message, toUser);
        }
    }

    /**
     * 普通消息
     */
    public static class CommonMessage extends AbstractMessage {

        public CommonMessage(MessageSender sender) {
            super(sender);
        }

        @Override
        public void sendMessage(String message, String toUser) {
            // 对于普通消息,直接调用父类方法,发送消息即可
            super.sendMessage(message, toUser);
        }
    }

    /**
     * 加急消息
     */
    public static class UrgencyMessage extends AbstractMessage {

        public UrgencyMessage(MessageSender sender) {
            super(sender);
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
     * 消息传送
     */
    public interface MessageSender {
        /**
         * 发送消息
         */
        void send(String message, String toUser);
    }

    /**
     * 系统内消息
     */
    public static class SMSSender implements MessageSender {

        @Override
        public void send(String message, String toUser) {

            log.info("使用系统内短消息的方法,{} 发送消息给 {}", message, toUser);
        }
    }

    /**
     * 邮件消息
     */
    public static class EmailSender implements MessageSender {

        @Override
        public void send(String message, String toUser) {
            log.info("使用邮件短消息的方法,{} 发送消息给 {}", message, toUser);
        }
    }

    /**
     * 手机消息
     */
    public static class MobileSender implements MessageSender {

        @Override
        public void send(String message, String toUser) {
            log.info("使用手机的方法,{} 发送消息给 {}", message, toUser);
        }
    }
}
