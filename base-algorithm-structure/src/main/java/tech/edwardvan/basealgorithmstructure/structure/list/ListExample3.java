package tech.edwardvan.basealgorithmstructure.structure.list;

/**
 * 改进链表,添加tail成员变量,使addLast方法时间复杂度为O(1)
 */
public class ListExample3 {

    public static class MyLinkedList<E> {
        // 链表头部
        private Node head;
        // 链表尾部
        private Node tail;
        // 节点数量
        private int size;

        public MyLinkedList() {
            head = null;
            tail = null;
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


        // 在链表末尾添加新的元素e
        // 时间复杂度: O(1)
        public void addLast(E e) {
            if (tail == null) {
                tail = new Node(e);
                head = tail;
            } else {
                tail.next = new Node(e);
                tail = tail.next;
            }
            size++;
        }

        // 获得链表的第一个元素
        public E getFirst() {
            if (isEmpty())
                throw new IllegalArgumentException("链表为空");
            return head.e;
        }


        // 从链表中删除第一个元素, 返回删除的元素
        // 时间复杂度: O(1)
        public E removeFirst() {
            if (isEmpty())
                throw new IllegalArgumentException("链表为空");
            Node delNode = head;
            head = head.next;
            delNode.next = null;
            if (head == null)
                tail = null;
            size--;
            return delNode.e;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            Node cur = head;
            while (cur != null) {
                res.append(cur + "->");
                cur = cur.next;
            }
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
