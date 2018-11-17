package tech.edwardvan.basealgorithmstructure.structure.queue;

/**
 * 数组实现循环队列
 * 特性: 当 front==tail 时表示队列为空, 当 (tail + 1) % data.length == front 时表示队列已满
 * 优点: dequeue操作均摊时间复杂度为O(1)
 */
public class QueueExample2 {
    public static void main(String[] args) {
        MyLoopQueue<Integer> queue = new MyLoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    public static class MyLoopQueue<E> implements Queue<E> {
        private E[] data;
        //front指向第一个元素的位置
        private int front;
        //tail指向新元素放置的位置
        private int tail;
        private int size;

        public MyLoopQueue() {
            this(10);
        }

        public MyLoopQueue(int capacity) {
            //LoopQueue会浪费一个元素的空间,所以创建的数组时需要+1
            data = (E[]) new Object[capacity + 1];
            front = 0;
            tail = 0;
            size = 0;
        }

        //获取队列的容量
        public int getCapacity() {
            return data.length - 1;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return front == tail;
        }

        @Override
        public void enqueue(E e) {
            //如果队列已满
            if ((tail + 1) % data.length == front)
                resize(getCapacity() * 2);
            data[tail] = e;
            tail = (tail + 1) % data.length;
            size++;
        }

        @Override
        public E dequeue() {
            if (isEmpty())
                throw new IllegalArgumentException("队列为空");
            E ret = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;
            if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
                resize(getCapacity() / 2);
            return ret;
        }

        @Override
        public E getFront() {
            if (isEmpty())
                throw new IllegalArgumentException("队列为空");
            return data[front];
        }

        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity + 1];
            for (int i = 0; i < size; i++)
                newData[i] = data[(front + i) % data.length];
            front = 0;
            tail = size;
            data = newData;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
            res.append("front [");
            for (int i = front; i != tail; i = (i + 1) % data.length) {
                res.append(data[i]);
                if ((i + 1) % data.length != tail)
                    res.append(", ");
            }
            res.append("] tail");
            return res.toString();
        }


    }

}
