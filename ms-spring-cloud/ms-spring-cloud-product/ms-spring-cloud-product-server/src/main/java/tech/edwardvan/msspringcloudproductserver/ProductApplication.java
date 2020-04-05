package tech.edwardvan.msspringcloudproductserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Idea启动参数:
 * --server.port=8081
 * --server.port=8082
 *
 * @author EdwardVan
 */
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
