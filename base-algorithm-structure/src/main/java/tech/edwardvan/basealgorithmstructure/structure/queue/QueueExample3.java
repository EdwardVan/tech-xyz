package tech.edwardvan.basealgorithmstructure.structure.queue;

import tech.edwardvan.basealgorithmstructure.structure.list.ListExample3;

/**
 * 链表实现队列
 */
public class QueueExample3 {
    public static void main(String[] args) {
        MyListQueue<Integer> queue = new MyListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    public static class MyListQueue<E> implements Queue<E> {

        ListExample3.MyLinkedList<E> list;

        public MyListQueue() {
            list = new ListExample3.MyLinkedList<>();
        }

        @Override
        public int getSize() {
            return list.getSize();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            list.addLast(e);
        }

        @Override
        public E dequeue() {
            return list.removeFirst();
        }

        @Override
        public E getFront() {
            return list.getFirst();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Queue: front ");
            res.append(list.toString());
            res.append(" tail");
            return res.toString();
        }
    }

}
