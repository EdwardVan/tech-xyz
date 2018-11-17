package tech.edwardvan.basealgorithmstructure.structure.stack;

/**
 * 栈规范接口
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
