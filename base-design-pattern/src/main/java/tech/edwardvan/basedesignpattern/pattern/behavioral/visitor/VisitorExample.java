package tech.edwardvan.basedesignpattern.pattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 访问者模式
 * <p>
 * 优点:
 * 符合单一职责原则
 * 优秀的扩展性
 * 缺点:
 * 违反了最少知道原则
 * 具体元素变更比较困难
 * 违反了依赖倒置原则
 * 举例:
 * {@link java.nio.file.FileVisitor}
 * {@link org.springframework.beans.factory.config.BeanDefinitionVisitor}
 *
 * @author EdwardVan
 */
public class VisitorExample {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);
    }

    /**
     * 抽象访问者
     */
    interface Visitor {

        void visit(ConcreteElementA element);

        void visit(ConcreteElementB element);
    }

    /**
     * 具体访问者B类
     */
    static class ConcreteVisitorA implements Visitor {
        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者A访问-->" + element.operationA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者A访问-->" + element.operationB());
        }
    }

    /**
     * 具体访问者B类
     */
    static class ConcreteVisitorB implements Visitor {
        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者B访问-->" + element.operationA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者B访问-->" + element.operationB());
        }
    }


    /**
     * 抽象元素类
     */
    interface Element {
        void accept(Visitor visitor);
    }

    /**
     * 具体元素A
     */
    static class ConcreteElementA implements Element {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationA() {
            return "具体元素A的操作";
        }
    }

    /**
     * 具体元素B
     */
    static class ConcreteElementB implements Element {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationB() {
            return "具体元素B的操作";
        }
    }

    /**
     * 对象结构角色
     */
    static class ObjectStructure {

        private List<Element> list = new ArrayList<>();

        public void accept(Visitor visitor) {
            Iterator<Element> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().accept(visitor);
            }
        }

        public void add(Element element) {
            list.add(element);
        }

        public void remove(Element element) {
            list.remove(element);
        }
    }

}
