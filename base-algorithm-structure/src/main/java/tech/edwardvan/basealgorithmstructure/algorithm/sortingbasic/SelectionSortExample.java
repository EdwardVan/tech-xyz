package tech.edwardvan.basealgorithmstructure.algorithm.sortingbasic;


import tech.edwardvan.util.ArrayUtil;

/**
 * 选择排序
 * 每一趟从待排序的数据元素中选择最小的一个元素作为首元素,直到所有元素排完为止
 */
public class SelectionSortExample {

    //排序
    public static void sort(Comparable[] arr) {
        //依次对第i个位置的放置元素
        for (int i = 0; i < arr.length; i++) {
            int miniIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[miniIndex]) < 0) {
                    miniIndex = j;
                }
            }
            //将最小值位置和第i个位置的值交换
            ArrayUtil.swap(arr, i, miniIndex);
        }
    }


    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.generateRandomArray(20, 0, 20);
        ArrayUtil.print(arr);
        sort(arr);
        ArrayUtil.print(arr);
    }
}


