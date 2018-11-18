package tech.edwardvan.basealgorithmstructure.structure.stack;

import tech.edwardvan.basealgorithmstructure.structure.list.ListExample2;

/**
 * 链表实现栈
 */
public class StackExample2 {
    public static void main(String[] args) {
        MyListStack<Integer> stack = new MyListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }

    static public class MyListStack<E> implements Stack<E> {

        ListExample2.MyLinkedList<E> list;

        public MyListStack() {
            list = new ListExample2.MyLinkedList<>();
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
        public void push(E e) {
            list.addFirst(e);
        }

        @Override
        public E pop() {
            return list.removeFirst();
        }

        @Override
        public E peek() {
            return list.getFirst();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Stack: top ");
            res.append(list);
            return res.toString();
        }
    }
}
