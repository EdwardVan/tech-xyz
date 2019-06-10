package tech.edwardvan.basedesignpattern.pattern.behavioral.templatemethod;


/**
 * 模板方法
 * 举例:
 * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer#tryAcquire(int)}
 *
 * @author EdwardVan
 */
public class TemplateMethodExample {
    public static void main(String[] args) {
        AbstractDevelopmentProcess independentDeveloper = new IndependentDeveloper();
        independentDeveloper.execute();
        AbstractDevelopmentProcess companyDeveloper = new CompanyDeveloper();
        companyDeveloper.execute();

    }

    /**
     * 开发流程抽象类
     */
    public static abstract class AbstractDevelopmentProcess {
        /**
         * 设计
         */
        public abstract void design();

        /**
         * 开发
         */
        public abstract void develop();

        /**
         * 发布
         */
        public final void release() {
            System.out.println("发布App到各大市场");
        }

        /**
         * 执行流程
         */
        public void execute() {
            design();
            develop();
            release();
        }
    }

    /**
     * 独立开发者
     */
    public static class IndependentDeveloper extends AbstractDevelopmentProcess {
        @Override
        public void design() {
            System.out.println("自己设计App");
        }

        @Override
        public void develop() {
            System.out.println("独立开发App");
        }
    }

    /**
     * 公司员工开发者
     */
    public static class CompanyDeveloper extends AbstractDevelopmentProcess {
        @Override
        public void design() {
            System.out.println("产品设计App原型");
            System.out.println("设计师设计App UI");
        }

        @Override
        public void develop() {
            System.out.println("团队共同开发App");
        }
    }
}
