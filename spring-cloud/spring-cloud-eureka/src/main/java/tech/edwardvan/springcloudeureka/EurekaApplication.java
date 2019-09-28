package tech.edwardvan.springcloudeureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka注册中心启动类
 *
 * Idea启动参数:
 * --server.port=8762 --eureka.instance.hostname=eureka-peer2 --eureka.client.service-url.defaultZone=http://eureka-peer1:8761/eureka/
 * --server.port=8761 --eureka.instance.hostname=eureka-peer1 --eureka.client.service-url.defaultZone=http://eureka-peer2:8762/eureka/
 * @author EdwardVan
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

}
