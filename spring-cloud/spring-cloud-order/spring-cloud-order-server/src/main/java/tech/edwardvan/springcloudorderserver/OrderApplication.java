package tech.edwardvan.springcloudorderserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author EdwardVan
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"tech.edwardvan.springcloud.client"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
