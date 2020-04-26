package tech.edwardvan.basedesignpattern.pattern.behavioral.chainofresponsibility;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 责任链模式
 *
 * @author EdwardVan
 */
@Slf4j
public class ChainofResponsibilityExample2 {

    public static void main(String[] args) {
        //组装责任链
        LeaderChain leaderChain = new LeaderChain().addLeader(new ClassAdviser()).addLeader(new DepartmentHead()).addLeader(new Dean());
        leaderChain.handleRequest(1);
        leaderChain.handleRequest(5);
        leaderChain.handleRequest(11);
    }

    /**
     * 领导链
     */
    public static class LeaderChain {

        private List<Leader> leaders = new ArrayList<>();

        private Iterator<Leader> iterator;

        public LeaderChain addLeader(Leader leader) {
            leaders.add(leader);
            return this;
        }

        public void handleRequest(int leaveDays) {
            // 初始化迭代器
            if (iterator == null) {
                iterator = leaders.iterator();
            }
            if (iterator.hasNext()) {
                iterator.next().handleRequest(leaveDays, this);
            }
        }
    }

    /**
     * 抽象处理者:领导类
     */
    public interface Leader {
        /**
         * 处理请求的方法
         */
        void handleRequest(int leaveDays, LeaderChain leaderChain);
    }

    /**
     * 班主任
     */
    public static class ClassAdviser implements Leader {
        @Override
        public void handleRequest(int leaveDays, LeaderChain leaderChain) {
            if (leaveDays <= 2) {
                log.info("班主任批准您请假{}天.", leaveDays);
            } else {
                leaderChain.handleRequest(leaveDays);
            }
        }
    }

    /**
     * 系主任
     */
    public static class DepartmentHead implements Leader {
        @Override
        public void handleRequest(int leaveDays, LeaderChain leaderChain) {
            if (leaveDays <= 7) {
                log.info("系主任批准您请假{}天.", leaveDays);
            } else {
                leaderChain.handleRequest(leaveDays);
            }
        }
    }

    /**
     * 院长
     */
    public static class Dean implements Leader {
        @Override
        public void handleRequest(int leaveDays, LeaderChain leaderChain) {
            if (leaveDays <= 10) {
                log.info("院长批准您请假{}天.", leaveDays);
            } else {
                log.info("请假天数太多,没有人批准该假条!");
            }
        }
    }

}
