package tech.edwardvan.basealgorithmstructure.algorithm.sortingbasic;

import tech.edwardvan.util.ArrayUtil;

/**
 * 冒泡排序
 * 对相邻的元素进行两两比较,顺序相反则进行交换,每一趟会将最大的元素"浮"到顶端,最终达到完全有序
 */
public class BubbleSortExample {

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    ArrayUtil.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.generateRandomArray(20, 0, 20);
        ArrayUtil.print(arr);
        sort(arr);
        ArrayUtil.print(arr);
    }
}
