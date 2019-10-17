package tech.edwardvan.basealgorithmstructure.algorithm.sortingadvance;

import tech.edwardvan.basealgorithmstructure.util.ArrayUtil;

/**
 * 快速排序
 */
public class QuickSortExample {

    public static void sort(Comparable[] arr, int left, int right) {
        if (right - left <= 0)
            return;
        //定义基准元素temp
        Comparable temp = arr[left];
        int i = left;
        int j = right;
        //寻找temp元素的位置
        while (i != j) {
            while (i < j && arr[j].compareTo(temp) >= 0)
                j--;
            arr[i] = arr[j];
            while (i < j && arr[i].compareTo(temp) < 0)
                i++;
            arr[j] = arr[i];
        }
        //将temp元素放置对应的位置
        arr[i] = temp;
        //分别将temp元素两边的数组进行快速排序
        sort(arr, left, i - 1);
        sort(arr, i + 1, right);
    }

    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.generateRandomArray(21, 0, 20);
        ArrayUtil.print(arr);
        sort(arr, 0, arr.length - 1);
        ArrayUtil.print(arr);
    }
}
