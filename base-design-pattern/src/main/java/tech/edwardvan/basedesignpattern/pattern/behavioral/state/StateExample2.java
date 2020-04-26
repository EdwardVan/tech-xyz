package tech.edwardvan.basedesignpattern.pattern.behavioral.state;

import lombok.extern.slf4j.Slf4j;

/**
 * 状态模式
 *
 * @author EdwardVan
 */
@Slf4j
public class StateExample2 {

    public static void main(String[] args) {
        //使用状态模式
        Context context = new Context();
        context.close();
        context.open();
        context.run();
        context.stop();
    }

    /**
     * 电梯状态接口
     */
    public static abstract class LiftState {
        /**
         * 环境类
         */
        protected Context context;

        public LiftState(Context context) {
            this.context = context;
        }

        /**
         * 开门
         */
        public abstract void open();

        /**
         * 关门
         */
        public abstract void close();

        /**
         * 运行
         */
        public abstract void run();

        /**
         * 停止
         */
        public abstract void stop();
    }

    /**
     * 开门状态
     */
    public static class OpeningState extends LiftState {

        public OpeningState(Context context) {
            super(context);
        }

        @Override
        public void open() {
            log.info("电梯门已经打开,无需再次打开");
        }

        @Override
        public void close() {
            context.setLiftState(context.CLOSING_STATE);
            log.info("电梯门关闭");
        }

        @Override
        public void run() {
            log.info("开门时电梯不能运行");
        }

        @Override
        public void stop() {
            log.info("开门时电梯已经停止,无需再次停止");
        }
    }

    /**
     * 关门状态
     */
    public static class ClosingState extends LiftState {

        public ClosingState(Context context) {
            super(context);
        }

        @Override
        public void open() {
            context.setLiftState(context.OPENING_STATE);
            log.info("电梯门打开");
        }

        @Override
        public void close() {
            log.info("电梯门已经关闭,无需再次关闭");
        }

        @Override
        public void run() {
            context.setLiftState(context.RUNNING_STATE);
            log.info("电梯开始运行");
        }

        @Override
        public void stop() {
            context.setLiftState(context.STOPPING_STATE);
            log.info("电梯停止");
        }
    }

    /**
     * 运行状态
     */
    public static class RunningState extends LiftState {


        public RunningState(Context context) {
            super(context);
        }

        @Override
        public void open() {
            log.info("运行时电梯不能开门");
        }

        @Override
        public void close() {
            log.info("运行时电梯门已经关闭,无需再次关闭");
        }

        @Override
        public void run() {
            log.info("运行时电梯已经运行,无需再次运行");
        }

        @Override
        public void stop() {
            context.setLiftState(context.STOPPING_STATE);
            log.info("电梯停止");
        }
    }

    /**
     * 停止状态
     */
    public static class StoppingState extends LiftState {

        public StoppingState(Context context) {
            super(context);
        }

        @Override
        public void open() {
            context.setLiftState(context.OPENING_STATE);
            log.info("电梯门打开");
        }

        @Override
        public void close() {
            log.info("停止时电梯门已经关闭,无需再次关闭");
        }

        @Override
        public void run() {
            context.setLiftState(context.RUNNING_STATE);
            log.info("电梯开始运行");
        }

        @Override
        public void stop() {
            log.info("停止时电梯已经停止,无需再次停止");
        }
    }

    /**
     * 环境类
     */
    public static class Context {
        /**
         * 开门状态
         */
        LiftState OPENING_STATE;
        /**
         * 关门状态
         */
        LiftState CLOSING_STATE;
        /**
         * 运行状态
         */
        LiftState RUNNING_STATE;
        /**
         * 停止状态
         */
        LiftState STOPPING_STATE;

        {
            OPENING_STATE = new OpeningState(this);
            CLOSING_STATE = new ClosingState(this);
            RUNNING_STATE = new RunningState(this);
            STOPPING_STATE = new StoppingState(this);
        }

        private LiftState liftState;

        {
            // 默认为开门状态
            liftState = OPENING_STATE;
        }

        public void setLiftState(LiftState liftState) {
            this.liftState = liftState;
        }

        /**
         * 开门
         */
        public void open() {
            liftState.open();
        }

        /**
         * 关门
         */
        public void close() {
            liftState.close();
        }

        /**
         * 运行
         */
        public void run() {
            liftState.run();
        }

        /**
         * 停止
         */
        public void stop() {
            liftState.stop();
        }
    }

}
