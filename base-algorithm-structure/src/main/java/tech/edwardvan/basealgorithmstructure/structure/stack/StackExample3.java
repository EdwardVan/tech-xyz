package tech.edwardvan.basealgorithmstructure.structure.stack;

import java.util.Random;

/**
 * 比较不同栈的效率
 */
public class StackExample3 {
    // 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
    private static double testStack(Stack<Integer> stack, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            stack.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        StackExample.MyArrayStack<Integer> arrayStack = new StackExample.MyArrayStack<>();
        System.out.println("ArrayStack, time: " + testStack(arrayStack, opCount) + " s");

        StackExample2.MyListStack<Integer> listStack = new StackExample2.MyListStack<>();
        System.out.println("LinkedListStack, time: " + testStack(listStack, opCount) + " s");

        // 其实这个时间比较很复杂，因为LinkedListStack中包含更多的new操作
    }
}
