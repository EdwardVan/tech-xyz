package tech.edwardvan.basealgorithmstructure.structure.list;

/**
 * 使用虚拟头节点的链表
 */
public class ListExample2 {
    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }

    public static class MyLinkedList<E> {
        // 链表头部前一个元素(虚拟节点)
        private Node dummyHead;
        // 节点数量
        private int size;

        public MyLinkedList() {
            dummyHead = new Node();
            size = 0;
        }

        // 获取链表中的元素个数
        public int getSize() {
            return size;
        }

        // 返回链表是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        // 在链表头添加新的元素e
        // 时间复杂度: O(1)
        public void addFirst(E e) {
            add(0, e);
        }

        // 在链表末尾添加新的元素e
        // 时间复杂度: O(n)
        public void addLast(E e) {
            add(size, e);
        }

        // 在链表的index位置添加新的元素e
        // 时间复杂度: O(n)
        public void add(int index, E e) {
            if (index < 0 || index > size)
                throw new IllegalArgumentException("Add failed. Illegal index.");
            Node prev = dummyHead;
            for (int i = 0; i < index; i++)
                prev = prev.next;
            prev.next = new Node(e, prev.next);
            size++;
        }

        // 获得链表的第index个位置的元素
        public E get(int index) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("Get failed. Illegal index.");
            Node temp = dummyHead.next;
            for (int i = 0; i < index; i++)
                temp = temp.next;
            return temp.e;
        }

        // 获得链表的第一个元素
        public E getFirst() {
            return get(0);
        }

        // 获得链表的最后一个元素
        public E getLast() {
            return get(size - 1);
        }

        // 修改链表的第index(0-based)个位置的元素为e
        // 时间复杂度: O(n)
        public void set(int index, E e) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("Set failed. Illegal index.");
            Node temp = dummyHead.next;
            for (int i = 0; i < index; i++)
                temp = temp.next;
            temp.e = e;
        }

        // 查找链表中是否有元素e
        public boolean contains(E e) {
            Node temp = dummyHead.next;
            for (int i = 0; i < size; i++) {
                if (temp.e.equals(e))
                    return true;
                temp = temp.next;
            }
            return false;
        }

        // 从链表中删除index位置的元素, 返回删除的元素
        // 时间复杂度: O(n)
        public E remove(int index) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("Add failed. Illegal index.");
            Node prev = dummyHead;
            for (int i = 0; i < index; i++)
                prev = prev.next;
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.e;
        }

        // 从链表中删除第一个元素, 返回删除的元素
        // 时间复杂度: O(1)
        public E removeFirst() {
            return remove(0);
        }

        // 从链表中删除最后一个元素, 返回删除的元素
        // 时间复杂度: O(n)
        public E removeLast() {
            return remove(size - 1);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            for (Node cur = dummyHead.next; cur != null; cur = cur.next)
                res.append(cur + "->");
            res.append("NULL");
            return res.toString();
        }

        //链表节点
        private class Node {
            public E e;
            public Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }

            public Node(E e) {
                this(e, null);
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return e.toString();
            }
        }
    }
}
