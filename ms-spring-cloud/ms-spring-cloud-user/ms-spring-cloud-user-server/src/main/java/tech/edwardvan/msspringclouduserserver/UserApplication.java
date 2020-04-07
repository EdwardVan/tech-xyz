package tech.edwardvan.msspringclouduserserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Idea启动参数:
 * --server.port=8081
 * --server.port=8082
 *
 * @author EdwardVan
 */
@SpringBootApplication
@EnableFeignClients("tech.edwardvan.msspringcloudclient.client")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
