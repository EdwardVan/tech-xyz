package tech.edwardvan.basejava.generics;

/**
 * 泛型接口
 */
public class GenericsInterfaceExample {

    //泛型接口
    interface IContainer<T> {
        T get();

        void set(T t);
    }

    //实现接口不指定泛型
    static class Container implements IContainer {
        private Object o;

        @Override
        public Object get() {
            return o;
        }

        @Override
        public void set(Object o) {
            this.o = o;
        }
    }

    //实现接口指定泛型
    static class Container2 implements IContainer<String> {
        private String s;

        @Override
        public String get() {
            return s;
        }

        @Override
        public void set(String s) {
            this.s = s;
        }
    }

    //实现接口保留泛型
    static class Container3<T> implements IContainer<T> {
        private T t;

        @Override
        public T get() {
            return t;
        }

        @Override
        public void set(T t) {
            this.t = t;
        }
    }

}
