package tech.edwardvan.webdubboprovider;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartService {
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            context.start();
            System.out.println("web-dubbo-provider微服务启动成功!");
            // 禁止服务停止,线程睡眠可以降低CPU资源消耗1/4
            for (;;) {
                Thread.sleep(Long.MAX_VALUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
