package tech.edwardvan.baseconcurrent.atomic;

import tech.edwardvan.baseconcurrent.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater示例
 */
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            System.out.println("update success 1,:" + example5.getCount());
        }

        if (updater.compareAndSet(example5, 110, 140)) {
            System.out.println("update success 2:" + example5.getCount());
        } else {
            System.out.println("update failed:" + example5.getCount());
        }
    }
}
