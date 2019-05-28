package tech.edwardvan.basedesignpattern.pattern.structural.facade;

/**
 * 外观模式
 * 优点:
 * 对客户屏蔽子系统组件,减少了客户处理的对象数目并使得子系统使用起来更加容易.通过引入外观模式,客户代码将变得很简单,与之关联的对象也很少.
 * 实现了子系统与客户之间的松耦合关系,这使得子系统的组件变化不会影响到调用它的客户类,只需要调整外观类即可.
 * 只是提供了一个访问子系统的统一入口,并不影响用户直接使用子系统类.
 * 符合最少知道原则
 * 缺点:
 * 如果设计不当,增加新的子系统可能需要修改门面类的源代码,违背了开闭原则.
 *
 * @author EdwardVan
 */
public class FacadeExample {
    public static void main(String[] args) {
        // 不符合最少知道原则
        HealthOffice healthOffice = new HealthOffice();
        RevenueOffice revenueOffice = new RevenueOffice();
        SaicOffice saicOffice = new SaicOffice();
        healthOffice.approve();
        revenueOffice.approve();
        saicOffice.approve();
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
            System.out.println("卫生局通过审批");
        }
    }

    /**
     * 税务局
     */
    static class RevenueOffice implements Executive {
        @Override
        public void approve() {
            System.out.println("税务局完成登记");
        }
    }

    /**
     * 工商局
     */
    static class SaicOffice implements Executive {
        @Override
        public void approve() {
            System.out.println("工商局办理营业执照");
        }
    }
}
