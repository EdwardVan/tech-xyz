package tech.edwardvan.msspringcloudproductserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Idea启动参数:
 * --server.port=8091
 * --server.port=8092
 *
 * @author EdwardVan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("tech.edwardvan.msspringcloudclient.client")
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
