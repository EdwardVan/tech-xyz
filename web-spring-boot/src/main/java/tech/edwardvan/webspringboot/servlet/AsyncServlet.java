package tech.edwardvan.webspringboot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步Servlet
 *
 * @author EdwardVan
 */
@WebServlet(urlPatterns = "/asyncServlet", asyncSupported = true)
@Slf4j
public class AsyncServlet extends HttpServlet {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("doGet()准备执行");
        //开启异步,获取异步上下文
        final AsyncContext asyncContext = req.startAsync();

        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) {
                log.info("AsyncListener.onComplete()方法执行");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) {
                log.info("AsyncListener.onTimeout()方法执行");
            }

            @Override
            public void onError(AsyncEvent asyncEvent){
                log.info("AsyncListener.onError()方法执行");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent){
                log.info("AsyncListener.onStartAsync()方法执行");
            }
        });

        executorService.execute(() -> {
            log.info("Async Service 准备执行");
            try {
                //模拟耗时任务
                Thread.sleep(10000L);
                asyncContext.getResponse().getWriter().print("async servlet");

            } catch (Exception e) {
                e.printStackTrace();
            }
            //最后执行完成后完成回调
            asyncContext.complete();
            log.info("Async Service 执行结束");
        });

        log.info("doGet()执行结束");
    }
}
