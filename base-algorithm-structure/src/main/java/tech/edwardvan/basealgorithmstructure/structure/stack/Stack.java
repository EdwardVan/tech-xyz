package tech.edwardvan.basealgorithmstructure.structure.stack;

/**
 * 栈规范接口
 *
 * @author EdwardVan
 */
public interface Stack<E> {
    /**
     * 栈中元素个数
     */
    int getSize();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 入栈
     */
    void push(E e);

    /**
     * 出栈
     */
    E pop();

    /**
     * 返回栈顶元素
     */
    E peek();
}
