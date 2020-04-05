package tech.edwardvan.msspringclouduserserver;


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
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
