package tech.edwardvan.basejava.generics;

/**
 * 泛型方法
 */
public class GenericsMethodExample {

    public static void main(String[] args) {
        Container<String> c1 = new Container();
        c1.set("1");
        String cv1 = getContainerValue(c1);
        System.out.println(cv1);

        Container<Integer> c2 = new Container();
        c2.set(2);
        Integer cv2 = getContainerValue(c2);
        System.out.println(cv2);
    }

    static class Container<T> {
        private T t;

        public T get() {
            return t;
        }

        public void set(T t) {
            this.t = t;
        }
    }

    //泛型方法
    static <T> T getContainerValue(Container<T> c) {
        return c.get();
    }
}
