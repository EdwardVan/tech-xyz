package tech.edwardvan.basedesignpattern.pattern.behavioral.state;

/**
 * 状态模式
 * 优点:
 * 封装了转换规则.
 * 枚举可能的状态,在枚举状态之前需要确定状态种类.
 * 将所有与某个状态有关的行为放到一个类中,并且可以方便地增加新的状态,只需要改变对象状态即可改变对象的行为
 * 缺点:
 * 状态模式的使用必然会增加系统类和对象的个数.
 * 状态模式的结构与实现都较为复杂,如果使用不当将导致程序结构和代码的混乱.
 * 违反"开闭原则"
 * 举例:
 *
 * @author EdwardVan
 */
public class StateExample {

    public static void main(String[] args) {
        //不用状态模式
        Lift lift = new Lift(ILift.OPENING_STATE);
        lift.close();
        lift.open();
        lift.run();
        lift.stop();
    }

    /**
     * 电梯接口
     */
    public interface ILift {

        /**
         * 开门状态
         */
        int OPENING_STATE = 1;
        /**
         * 关门状态
         */
        int CLOSING_STATE = 2;
        /**
         * 运行状态
         */
        int RUNNING_STATE = 3;
        /**
         * 停止状态
         */
        int STOPPING_STATE = 4;

        /**
         * 设置电梯的状态
         */
        public void setState(int state);

        /**
         * 开门
         */
        public void open();

        /**
         * 关门
         */
        public void close();

        /**
         * 运行
         */
        public void run();

        /**
         * 停止
         */
        public void stop();
    }

    /**
     * 电梯实现类
     */
    public static class Lift implements ILift {
        /**
         * 状态标识
         */
        private int state;

        public Lift(int state) {
            this.state = state;
        }

        @Override
        public void setState(int state) {
            this.state = state;
        }

        @Override
        public void open() {
            switch (state) {
                case OPENING_STATE:
                    System.out.println("电梯门已经打开,无需再次打开");
                    break;
                case CLOSING_STATE:
                    this.setState(OPENING_STATE);
                    System.out.println("电梯门打开");
                    break;
                case RUNNING_STATE:
                    System.out.println("运行时电梯不能开门");
                    break;
                case STOPPING_STATE:
                    this.setState(OPENING_STATE);
                    System.out.println("电梯门打开");
                    break;
                default:
                    break;
            }
        }

        @Override
        public void close() {
            switch (state) {
                case OPENING_STATE:
                    this.setState(CLOSING_STATE);
                    System.out.println("电梯门关闭");
                    break;
                case CLOSING_STATE:
                    System.out.println("电梯门已经关闭,无需再次关闭");
                    break;
                case RUNNING_STATE:
                    System.out.println("运行时电梯门已经关闭,无需再次关闭");
                    break;
                case STOPPING_STATE:
                    System.out.println("停止时电梯门已经关闭,无需再次关闭");
                    break;
                default:
                    break;
            }
        }


        @Override
        public void run() {
            switch (state) {
                case OPENING_STATE:
                    System.out.println("开门时电梯不能运行");
                    break;
                case CLOSING_STATE:
                    this.setState(RUNNING_STATE);
                    System.out.println("电梯开始运行");
                    break;
                case RUNNING_STATE:
                    System.out.println("运行时电梯已经运行,无需再次运行");
                    break;
                case STOPPING_STATE:
                    this.setState(RUNNING_STATE);
                    System.out.println("电梯开始运行");
                    break;
                default:
                    break;
            }
        }

        @Override
        public void stop() {
            switch (state) {
                case OPENING_STATE:
                    System.out.println("开门时电梯已经停止,无需再次停止");
                    break;
                case CLOSING_STATE:
                    this.setState(STOPPING_STATE);
                    System.out.println("电梯停止");
                    break;
                case RUNNING_STATE:
                    this.setState(STOPPING_STATE);
                    System.out.println("电梯停止");
                    break;
                case STOPPING_STATE:
                    System.out.println("停止时电梯已经停止,无需再次停止");
                    break;
                default:
                    break;
            }
        }
    }

}
