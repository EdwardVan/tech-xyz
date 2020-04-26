package tech.edwardvan.basedesignpattern.pattern.behavioral.iterator;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式
 *
 * @author EdwardVan
 */
@Slf4j
public class IteratorExample {

    public static void main(String[] args) {
        ConcreteContainer concreteContainer = new ConcreteContainer();
        concreteContainer.add(1);
        concreteContainer.add(2);
        concreteContainer.add(3);
        Iterator iterator = concreteContainer.iterator();
        while (iterator.hasNext()) {
            log.info(iterator.next().toString());
        }
    }

    /**
     * 具体的容器
     */
    public static class ConcreteContainer {

        private List list;

        public ConcreteContainer() {
            list = new ArrayList();
        }

        public void add(Object obj) {
            list.add(obj);
        }

        public Iterator iterator() {
            return new ConcreteIterator();
        }

        public Object get(int index) {
            return list.get(index);
        }

        public int getSize() {
            return list.size();
        }

        /**
         * 具体的迭代器
         */
        public class ConcreteIterator implements Iterator {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if (index >= list.size()) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Object next() {
                Object object = list.get(index);
                index++;
                return object;
            }
        }

    }
}
