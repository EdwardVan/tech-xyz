package tech.edwardvan.webspringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * 异步处理器
 * Callable和DeferredResult可以用来进行异步请求处理
 * 在controller中返回相应的Callable或者DeferredResult,然后servlet线程将被释放,可用于其他请求连接
 *
 * @author EdwardVan
 */
@RestController
@RequestMapping("/async")
@EnableScheduling
@Slf4j
public class AsyncController {

    private final BlockingQueue<DeferredResult<String>> queue = new ArrayBlockingQueue<>(5);

    /**
     * 定时操作
     */
    @Scheduled(fixedRate = 5000)
    public void process() throws InterruptedException {
        DeferredResult<String> result = null;
        while (true) {
            log.info("AsyncController.process()开始执行");
            result = queue.take();
            Thread.sleep(5000);
            // 计算结果
            result.setResult("Hello,World");
            log.info("AsyncController.process()执行结束");
        }
    }

    /**
     * 原理:
     * {@link DeferredResultMethodReturnValueHandler}
     */
    @GetMapping("testDeferredResult")
    public DeferredResult<String> testDeferredResult() {
        log.info("AsyncController.testDeferredResult()开始执行");
        DeferredResult<String> result = new DeferredResult<>();
        // 入队操作
        queue.offer(result);
        result.onCompletion(() -> {
            log.info("result.onCompletion()开始执行");
        });
        log.info("AsyncController.testDeferredResult()执行结束");
        return result;
    }

    @GetMapping("/testCallable")
    public Callable<String> testCallable() {
        log.info("AsyncController.testCallable()开始执行");
        Callable callable = () -> {
            log.info("call()开始执行");
            Thread.sleep(5000);
            return "Hello,World";
        };
        log.info("AsyncController.testCallable()执行结束");
        return callable;
    }
}
