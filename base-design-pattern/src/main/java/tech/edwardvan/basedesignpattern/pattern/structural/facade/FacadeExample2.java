package tech.edwardvan.basedesignpattern.pattern.structural.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 外观模式
 *
 * @author EdwardVan
 */
@Slf4j
public class FacadeExample2 {
    public static void main(String[] args) {
        ApproveFacade approveFacade = new ApproveFacade();
        approveFacade.wholeApprove();
    }

    /**
     * 行政部门接口
     */
    interface Executive {
        void approve();
    }

    /**
     * 卫生局
     */
    static class HealthOffice implements Executive {
        @Override
        public void approve() {
            log.info("卫生局通过审批");
        }
    }

    /**
     * 税务局
     */
    static class RevenueOffice implements Executive {
        @Override
        public void approve() {
            log.info("税务局完成登记");
        }
    }

    /**
     * 工商局
     */
    static class SaicOffice implements Executive {
        @Override
        public void approve() {
            log.info("工商局办理营业执照");
        }
    }

    /**
     * 简化政务办理流程的门面
     */
    static class ApproveFacade {
        public void wholeApprove() {
            new HealthOffice().approve();
            new RevenueOffice().approve();
            new SaicOffice().approve();
        }
    }
}
