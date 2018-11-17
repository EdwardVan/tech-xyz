package tech.edwardvan.basealgorithmstructure.structure.array;

/**
 * 二次封装一个动态数组
 *
 * 时间复杂度
 * 概念:用来定性的描述算法的执行时间的一个函数,更类似于一个耗时的趋势,函数表示为:O(f(n))
 *
 * 名词解释:
 * n:问题的规模,重复执行的次数
 * T(n):一段程序运行,各种操作代码所执行的总次数
 * f(n):存在的某个函数,使得T(n)/f(n)=非零常数,那么f(n)称为T(n)的同数量级函数
 * O:大O符号,一种符号,表示渐进于无穷的行为
 *
 * 解释:
 * 算法中各种代码操作所执行的总次数用T(n)表示,存在某个函数f(n),使得T(n)/f(n)=非零常数
 * 那么f(n)称为T(n)的同数量级函数,即:T(n)=O(f(n)),O(f(n))就是时间复杂度.O符号表示一个渐进常数.(在这个函数中可以忽略低阶项和首项系数)
 */
public class ArrayExample2 {
    public static void main(String[] args) {
        MyArray<Integer> arr = new MyArray(2);
        for (int i = 0; i < 2; i++)
            arr.addLast(i);
        System.out.println(arr);

        arr.insert(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);
    }

    static public class MyArray<E> {
        private E[] data;
        private int size;

        public MyArray() {
            this(10);
        }

        public MyArray(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        //获取容器中元素个数
        public int getSize() {
            return size;
        }

        //获取数组的容量
        public int getCapacity() {
            return data.length;
        }

        //返回数组是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //向所有元素后添加一个新元素
        //时间复杂度: O(n) (因为有resize的操作)
        //假设capacity=n,n+1次addLast,触发一次resize,总共进行了2n+1次操作,平均每次addLast进行2次基本操作,所以addLast均摊复杂度为:O(1)
        public void addLast(E e) {
            insert(size, e);
        }

        //在所有元素前添加一个新元素
        //时间复杂度: O(n)
        public void addFirst(E e) {
            insert(0, e);
        }

        //在index索引的位置插入一个新元素e
        //时间复杂度: O(n)
        public void insert(int index, E e) {
            if (index < 0 || index > size)
                throw new IllegalArgumentException("index is error");
            if (size == data.length)
                resize(2 * data.length);
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = e;
            size++;
        }

        //获取index索引位置的元素
        //时间复杂度: O(1)
        public E get(int index) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("index is error");
            return data[index];
        }

        //获取最后一个位置的元素
        public E getLast() {
            return get(size - 1);
        }

        //获取第一个位置的元素
        public E getFirst() {
            return get(0);
        }


        //修改index索引位置的元素为e
        public void set(int index, E e) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("index is error");
            data[index] = e;
        }

        //查找数组中是否有元素e
        public boolean contains(E e) {
            for (E d : data) {
                if (d.equals(e)) {
                    return true;
                }
            }
            return false;
        }

        // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
        public int find(E e) {
            for (int i = 0; i < data.length; i++) {
                if (data[i].equals(e)) {
                    return i;
                }
            }
            return -1;
        }

        // 从数组中删除第一个元素, 返回删除的元素
        public E removeFirst() {
            return remove(0);
        }

        // 从数组中删除最后一个元素, 返回删除的元素
        // 当在临界状态下, 新增一个元素,删除一个元素,都触发了resize操作,复杂度从O(1)变成了O(n),这种现象称为复杂度震荡
        public E removeLast() {
            return remove(size - 1);
        }

        // 从数组中删除index位置的元素, 返回删除的元素
        public E remove(int index) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("index is error");
            E remove = data[index];
            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            size--;
            data[size] = null;//垃圾回收
            // 会产生复杂度震荡
            //if (size == data.length / 2)
            // 采用lazy机制 避免震荡
            if (size == data.length / 4 && data.length / 2 != 0)
                resize(data.length / 2);

            return remove;
        }

        // 将数组空间的容量变成newCapacity大小
        //时间复杂度: O(1)
        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        @Override
        public String toString() {

            StringBuilder res = new StringBuilder();
            res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
            res.append('[');
            for (int i = 0; i < size; i++) {
                res.append(data[i].toString());
                if (i != size - 1)
                    res.append(", ");
            }
            res.append(']');
            return res.toString();
        }
    }
}
