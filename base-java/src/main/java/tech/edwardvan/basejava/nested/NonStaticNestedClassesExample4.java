package tech.edwardvan.basejava.nested;

/**
 * lambda 表达式
 */
public class NonStaticNestedClassesExample4 {

    private static int i = 1;
    private int j = 10;

    public void f() {

        int k = 20;

        new Thread(() -> {
            System.out.println("i:" + i);
            System.out.println("j:" + j);
            System.out.println("k:" + k);
        }).start();
    }

    public static void main(String[] args) {
        new NonStaticNestedClassesExample4().f();
    }
}

