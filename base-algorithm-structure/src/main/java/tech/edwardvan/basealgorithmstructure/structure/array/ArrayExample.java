package tech.edwardvan.basealgorithmstructure.structure.array;

import tech.edwardvan.basealgorithmstructure.util.ArrayUtil;

/**
 * 数组的简单使用
 */
public class ArrayExample {
    public static void main(String[] args) {
        Integer[] a1 = new Integer[3];
        for (int i = 0; i < a1.length; i++)
            a1[i] = i;
        ArrayUtil.print(a1);

        Integer[] a2 = new Integer[]{3, 4, 5};
        ArrayUtil.print(a2);

        Integer[] a3 = {6, 7, 8};
        ArrayUtil.print(a3);
    }
}
