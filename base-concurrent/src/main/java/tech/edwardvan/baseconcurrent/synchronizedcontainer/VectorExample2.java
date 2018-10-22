package tech.edwardvan.baseconcurrent.synchronizedcontainer;

import tech.edwardvan.baseconcurrent.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * Vector示例
 * 两个线程安全的操作顺序执行不一定还是线程安全的
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            }).start();

            new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            }).start();
        }
    }
}
