package tech.edwardvan.basejava.nested;

/**
 * 匿名内部类
 * 1. 匿名内部类不能有构造方法。
 * 2. 匿名内部类不能定义任何静态成员、方法和类。
 * 3. 匿名内部类不能是public,protected,private,static。
 * 4. 只能创建匿名内部类的一个实例。
 * 5. 一个匿名内部类一定是在new的后面，用其隐含实现一个接口或实现一个类。
 * 6. 因匿名内部类为局部内部类，所以局部内部类的所有限制都对其生效。
 */
public class NonStaticNestedClassesExample3 {

    private static int i = 1;
    private int j = 10;

    public void f() {

        int k = 20;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i:" + i);
                System.out.println("j:" + j);
                System.out.println("k:" + k);
            }
        }).start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("i:" + i);
                System.out.println("j:" + j);
                System.out.println("k:" + k);
            }
        }.start();

    }

    public static void main(String[] args) {
        new NonStaticNestedClassesExample3().f();
    }
}

