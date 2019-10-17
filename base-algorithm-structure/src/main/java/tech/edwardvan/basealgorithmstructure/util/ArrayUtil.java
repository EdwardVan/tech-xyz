package tech.edwardvan.basealgorithmstructure.util;

/**
 * 数组工具类
 */
public class ArrayUtil {

    public static void main(String[] args) {
        Integer[] integers = generateRandomArray(10, 20, 30);
        print(integers);
    }

    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     *
     * @param n      数组元素个数
     * @param rangeL 随机数左边界
     * @param rangeR 随机数右边界
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        /*
            断言表达式
            (1)assert [boolean 表达式]
              如果[boolean表达式]为true,则程序继续执行.
              如果为false,则程序抛出AssertionError,并终止执行.
            (2)assert[boolean 表达式:错误表达式(日志)]
              如果[boolean表达式]为true,则程序继续执行.
              如果为false,则程序抛出java.lang.AssertionError,输出[错误信息].
         */
        assert n > 0 : "数组元素个数必须大于0";
        assert rangeL <= rangeR : "随机数左边界必须大于等于右边界";
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            /*
                Math.random()  [0.0,1.0)
                (int)(Math.random()*n)  [0,n)
                m+(int)(Matn.randon()*n) [m,m+n)或者[m,m+n-1]
             */
            arr[i] = (int) (rangeL + Math.random() * (rangeR - rangeL + 1));
        }
        return arr;
    }

    //输出数组

    /**
     * 数组输出到控制台
     *
     * @param arr 数组
     */
    public static void print(Object[] arr) {
        for (Object a : arr) {
            System.out.print(a);
            System.out.print(' ');
        }
        System.out.println();
    }

    /**
     * 数组元素位置交换
     *
     * @param arr 数组
     * @param i   下标i
     * @param j   下标j
     */
    public static void swap(Comparable[] arr, int i, int j) {
        if (i != j) {
            Comparable temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

}
