package tech.edwardvan.baseconcurrent.aqs;

import java.util.concurrent.FutureTask;
/**
 * FutureTask示例
 */
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("do something in callable");
            Thread.sleep(5000);
            return "Done";
        });

        new Thread(futureTask).start();
        System.out.println("do something in main");
        String result = futureTask.get();
        System.out.println("result:" + result);
    }
}
