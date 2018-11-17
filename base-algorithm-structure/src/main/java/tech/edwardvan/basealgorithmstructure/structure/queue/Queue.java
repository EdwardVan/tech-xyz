package tech.edwardvan.basealgorithmstructure.structure.queue;

/**
 * 队列规范接口
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
