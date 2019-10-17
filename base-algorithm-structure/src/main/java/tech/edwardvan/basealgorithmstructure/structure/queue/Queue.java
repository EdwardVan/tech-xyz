package tech.edwardvan.basealgorithmstructure.structure.queue;

/**
 * 队列规范接口
 *
 * @author EdwardVan
 */
public interface Queue<E> {
    /**
     * 队列中元素个数
     */
    int getSize();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 入队
     */
    void enqueue(E e);

    /**
     * 出队
     */
    E dequeue();

    /**
     * 获得队首元素
     */
    E getFront();
}
