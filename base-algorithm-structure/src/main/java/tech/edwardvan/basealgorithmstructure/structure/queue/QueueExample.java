package tech.edwardvan.basealgorithmstructure.structure.queue;

import tech.edwardvan.basealgorithmstructure.structure.array.ArrayExample2;

/**
 * 数组实现队列
 * 缺点: dequeue操作时间复杂度为O(n)
 */
public class QueueExample {
    public static void main(String[] args) {
        MyArrayQueue<Integer> queue = new MyArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    public static class MyArrayQueue<E> implements Queue<E> {

        ArrayExample2.MyArray<E> array;

        public MyArrayQueue() {
            array = new ArrayExample2.MyArray();
        }

        public MyArrayQueue(int capacity) {
            array = new ArrayExample2.MyArray(capacity);
        }

        @Override
        public int getSize() {
            return array.getSize();
        }

        @Override
        public boolean isEmpty() {
            return array.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            array.addLast(e);
        }

        //时间复杂度: O(n)
        @Override
        public E dequeue() {
            return array.removeFirst();
        }

        @Override
        public E getFront() {
            return array.getFirst();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Queue: ");
            res.append("front [");
            for (int i = 0; i < array.getSize(); i++) {
                res.append(array.get(i));
                if (i != array.getSize() - 1)
                    res.append(", ");
            }
            res.append("] tail");
            return res.toString();
        }
    }

}
