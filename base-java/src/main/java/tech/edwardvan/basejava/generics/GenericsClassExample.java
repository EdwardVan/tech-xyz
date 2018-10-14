package tech.edwardvan.basejava.generics;

/**
 * 泛型类
 * 类型擦除
 * Java中的泛型基本上都是在编译器这个层次来实现的
 * 在生成的Java字节代码中是不包含泛型中的类型信息的
 * 使用泛型的时候加上的类型参数,会被编译器在编译的时候去掉,这个过程就称为类型擦除
 * 比如并不存在List<String>.class或是List<Integer>.class,而只有List.class
 * 类型擦除的基本过程也比较简单,首先是找到用来替换类型参数的具体类
 * 这个具体类一般是Object,如果指定了类型参数的上界的话,则使用这个上界
 */
public class GenericsClassExample {

    public static void main(String[] args) {
        //不指定泛型类型
        Container c1 = new Container();
        c1.set(1);
        c1.set("1");
        Object o1 = c1.get();
        //指定泛型类型
        Container<String> c2 = new Container<>();
        //c2.set(1);//error
        c2.set("1");
        String o2 = c2.get();
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

    // 继承时不指定泛型
    static class ContainerChild1 extends Container {
        @Override
        public Object get() {
            return super.get();
        }
    }

    // 继承时指定泛型
    static class ContainerChild2 extends Container<String> {
        @Override
        public String get() {
            return super.get();
        }
    }

    // 继承泛型
    static class ContainerChild3<T> extends Container<T> {
        @Override
        public T get() {
            return super.get();
        }
    }


}
