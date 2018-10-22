package tech.edwardvan.baseconcurrent.deadlock;


/**
 * 死锁示例
 */
public class DeadLockExample {

    private static Object o1 = new Object();

    private static Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o1) {
                System.out.println("hello 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("hello 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o2) {
                System.out.println("hello 3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("hello 4");
                }
            }
        }).start();
    }

}
