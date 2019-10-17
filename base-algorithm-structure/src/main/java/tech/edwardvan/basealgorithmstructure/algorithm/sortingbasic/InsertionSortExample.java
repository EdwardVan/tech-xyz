package tech.edwardvan.basealgorithmstructure.algorithm.sortingbasic;


import tech.edwardvan.basealgorithmstructure.util.ArrayUtil;

/**
 * 插入排序
 * 每一步将一个待排序的记录,插入到前面已经排好序的有序序列中去,直到插完所有元素为止
 */
public class InsertionSortExample {

    public static void sort(Comparable[] arr) {
        //依次对第i个位置的元素操作(i之前的元素已经排序完成)
        for (int i = 1; i < arr.length; i++) {
            //验证j位置是否为arr[i]适合的插入的位置
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    ArrayUtil.swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    //优化版本(由三次赋值变为一次赋值)
    public static void sort2(Comparable[] arr) {
        //依次对第i个位置的元素操作(i之前的元素已经排序完成)
        for (int i = 1; i < arr.length; i++) {
            Comparable temp = arr[i];
            //验证j位置是否为arr[i]适合的插入的位置
            int j;
            for (j = i; j > 0; j--) {
                if (temp.compareTo(arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.generateRandomArray(20, 0, 20);
        ArrayUtil.print(arr);
        sort(arr);
        ArrayUtil.print(arr);
    }
}
