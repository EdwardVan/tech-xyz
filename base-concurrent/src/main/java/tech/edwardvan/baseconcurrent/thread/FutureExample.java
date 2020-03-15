package tech.edwardvan.baseconcurrent.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Future示例
 *
 * @author EdwardVan
 */
@Slf4j
public class FutureExample {

    public static void main(String[] args) {

        Callable<String> callable = () -> {
            log.info("do something in callable");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.info("抛出{}异常", InterruptedException.class.getName());
            }

//            throw new IllegalAccessException("测试异常");
            return "Done";
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(callable);
        log.info("future 是否执行完毕:{}", future.isDone());
        //中断任务
//        future.cancel(true);
        String result = null;
        try {
            result = future.get(4000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.info("抛出{}异常", InterruptedException.class.getName());
        } catch (ExecutionException e) {
            log.info("抛出{}异常", ExecutionException.class.getName());
        } catch (TimeoutException e) {
            log.info("抛出{}异常", TimeoutException.class.getName());
            //超时后取消任务,参数为true表示中断线程
            boolean cancel = future.cancel(true);
            log.info("cancel是否成功:{}", cancel);
        } catch (CancellationException e) {
            log.info("抛出{}异常", CancellationException.class.getName());
        }
        log.info("result:{}", result);
        executorService.shutdown();
    }
}
