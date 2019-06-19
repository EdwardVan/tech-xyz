package tech.edwardvan.basedesignpattern.pattern.behavioral.command;

/**
 * 命令模式
 * 优点:
 * 降低系统的耦合度.
 * 新的命令可以很容易地加入到系统中.
 * 缺点:
 * 使用命令模式可能会导致某些系统有过多的具体命令类.
 *
 * @author EdwardVan
 */
public class CommandExample {

    public static void main(String[] args) {
        // 创建接受者
        Receiver receiver = new Receiver();
        // 创建命令对象，并设置它的接受者
        Command command = new ConcreteCommand(receiver);

        // 创建调用者，将命令对象设置进去
        Invoker invoker = new Invoker();
        invoker.setCommand(command);

        invoker.runCommand();
    }

    /**
     * 抽象命令接口
     */
    public interface Command {
        /**
         * 执行命令
         */
        void execute();
    }

    /**
     * 具体命令类
     */
    public static class ConcreteCommand implements Command {

        private Receiver receiver;

        public ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.action();
        }
    }

    /**
     * 接收者
     */
    public static class Receiver {
        public void action() {
            System.out.println("执行命令");
        }
    }

    /**
     * 调用者
     */
    public static class Invoker {
        /**
         * 调用者持有命令对象
         */
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        /**
         * 执行命令
         */
        public void runCommand() {
            command.execute();
        }
    }
}
