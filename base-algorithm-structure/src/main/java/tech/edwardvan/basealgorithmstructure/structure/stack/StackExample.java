package tech.edwardvan.basealgorithmstructure.structure.stack;

import tech.edwardvan.basealgorithmstructure.structure.array.ArrayExample2;

/**
 * 数组实现栈
 * 仅允许在栈的一端进行插入和删除运算
 * 这一端被称为栈顶,相对地,把另一端称为栈底.向一个栈插入新元素又称作进栈、入栈或压栈
 * <p>
 * 实际应用:
 * 1.计算机程序栈
 * 2.撤回操作的实现
 *
 * @author EdwardVan
 */
public class StackExample {
    public static void main(String[] args) {
        MyArrayStack<Integer> stack = new MyArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }

    static public class MyArrayStack<E> implements Stack<E> {

        ArrayExample2.DynamicArray<E> array;

        public MyArrayStack() {
            array = new ArrayExample2.DynamicArray();
        }

        public MyArrayStack(int capacity) {
            array = new ArrayExample2.DynamicArray(capacity);
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
        public void push(E e) {
            array.addLast(e);
        }

        @Override
        public E pop() {
            return array.removeLast();
        }

        @Override
        public E peek() {
            return array.getLast();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Stack: ");
            res.append('[');
            for (int i = 0; i < array.getSize(); i++) {
                res.append(array.get(i));
                if (i != array.getSize() - 1)
                    res.append(", ");
            }
            res.append("] top");
            return res.toString();
        }
    }
}
