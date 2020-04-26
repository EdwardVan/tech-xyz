package tech.edwardvan.basedesignpattern.pattern.behavioral.chainofresponsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链模式
 * <p>
 * 有多个对象共同对一个任务进行处理.
 * 这些对象使用链式存储结构,形成一个链,每个对象知道自己的下一个对象.
 * 一个对象对任务进行处理,可以添加一些操作后将对象传递个下一个任务.也可以在此对象上结束任务的处理,并结束任务.
 * 客户端负责组装链式结构,但是客户端不需要关心最终是谁来处理了任务.
 * <p>
 * 举例:
 * {@link javax.servlet.FilterChain}
 *
 * @author EdwardVan
 */
@Slf4j
public class ChainofResponsibilityExample {

    public static void main(String[] args) {
        //组装责任链
        Leader teacher1 = new ClassAdviser();
        Leader teacher2 = new DepartmentHead();
        Leader teacher3 = new Dean();
        teacher1.setNext(teacher2);
        teacher2.setNext(teacher3);
        //提交请求
        teacher1.handleRequest(1);
        teacher1.handleRequest(5);
        teacher1.handleRequest(11);
    }

    /**
     * 抽象处理者:领导类
     */
    public static abstract class Leader {

        private Leader next;

        public void setNext(Leader next) {
            this.next = next;
        }

        public Leader getNext() {
            return next;
        }

        /**
         * 处理请求的方法
         */
        public abstract void handleRequest(int LeaveDays);
    }

    /**
     * 班主任
     */
    public static class ClassAdviser extends Leader {
        @Override
        public void handleRequest(int LeaveDays) {
            if (LeaveDays <= 2) {
                log.info("班主任批准您请假{}天.", LeaveDays);
            } else {
                if (getNext() != null) {
                    getNext().handleRequest(LeaveDays);
                } else {
                    log.info("请假天数太多,没有人批准该假条!");
                }
            }
        }
    }

    /**
     * 系主任
     */
    public static class DepartmentHead extends Leader {
        @Override
        public void handleRequest(int LeaveDays) {
            if (LeaveDays <= 7) {
                log.info("系主任批准您请假{}天.", LeaveDays);
            } else {
                if (getNext() != null) {
                    getNext().handleRequest(LeaveDays);
                } else {
                    log.info("请假天数太多,没有人批准该假条!");
                }
            }
        }
    }

    /**
     * 院长
     */
    public static class Dean extends Leader {
        @Override
        public void handleRequest(int LeaveDays) {
            if (LeaveDays <= 10) {
                log.info("院长批准您请假{}天.", LeaveDays);
            } else {
                if (getNext() != null) {
                    getNext().handleRequest(LeaveDays);
                } else {
                    log.info("请假天数太多,没有人批准该假条!");
                }
            }
        }
    }

}
