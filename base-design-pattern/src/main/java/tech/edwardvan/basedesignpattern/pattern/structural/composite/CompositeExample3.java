package tech.edwardvan.basedesignpattern.pattern.structural.composite;

import java.util.ArrayList;

/**
 * 组合模式
 *
 * @author EdwardVan
 */
public class CompositeExample3 {

    /**
     * 抽象构件
     */
    static abstract class Component {
        /**
         * 增加成员
         */
        public abstract void add(Component c);

        /**
         * 删除成员
         */
        public abstract void remove(Component c);

        /**
         * 获取成员
         */
        public abstract Component getChild(int i);

        /**
         * 业务方法
         */
        public abstract void operation();
    }

    /**
     * 叶子构件
     */
    static class Leaf extends Component {
        @Override
        public void add(Component c) {
            //异常处理或错误提示
        }

        @Override
        public void remove(Component c) {
            //异常处理或错误提示
        }

        @Override
        public Component getChild(int i) {
            //异常处理或错误提示
            return null;
        }

        @Override
        public void operation() {
            //叶子构件具体业务方法的实现
        }
    }

    /**
     * 容器构件
     */
    static class Composite extends Component {

        private ArrayList<Component> list = new ArrayList<>();

        @Override
        public void add(Component c) {
            list.add(c);
        }

        @Override
        public void remove(Component c) {
            list.remove(c);
        }

        @Override
        public Component getChild(int i) {
            return list.get(i);
        }

        @Override
        public void operation() {
            //容器构件具体业务方法的实现
            //递归调用成员构件的业务方法
            for (Object obj : list) {
                ((Component) obj).operation();
            }
        }
    }
}
