package tech.edwardvan.basealgorithmstructure.algorithm.sortingadvance;

import tech.edwardvan.util.ArrayUtil;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSortExample {

    public static void sort(Comparable[] arr) {
        if (arr.length > 1) {
            //1.分
            Comparable[] la = Arrays.copyOfRange(arr, 0, arr.length / 2);
            sort(la);
            Comparable[] ra = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
            sort(ra);

            //2.合
            if (la[la.length - 1].compareTo(ra[0]) > 0) {//当左边数组的最后一个元素大于右边数组第一个元素
                int lPointer = 0;
                int rPointer = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (lPointer < la.length && rPointer < ra.length) {
                        if (la[lPointer].compareTo(ra[rPointer]) < 0) {
                            arr[i] = la[lPointer];
                            lPointer++;
                        } else {
                            arr[i] = ra[rPointer];
                            rPointer++;
                        }
                    } else if (lPointer == la.length) {
                        arr[i] = ra[rPointer];
                        rPointer++;
                    } else if (rPointer == ra.length) {
                        arr[i] = la[lPointer];
                        lPointer++;
                    }
                }
            } else {
                for (int i = 0; i < la.length; i++) {
                    arr[i] = la[i];
                }
                for (int j = 0; j < ra.length; j++) {
                    arr[j + la.length] = ra[j];
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
