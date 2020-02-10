package tech.edwardvan.testspringbootautoconfigure.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 运行监听器
 * <p>
 * 加载:{@link SpringApplication#getRunListeners(String[])}
 *
 * @author EdwardVan
 */
@Slf4j
public class TestSpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    /**
     * 该方法必须存在,否则启动报错
     */
    public TestSpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        log.info("TestSpringApplicationRunListener.starting()");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("TestSpringApplicationRunListener.environmentPrepared()");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener.contextPrepared()");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener.contextLoaded()");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener.started()");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener.running()");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("TestSpringApplicationRunListener.failed()");
    }
}
