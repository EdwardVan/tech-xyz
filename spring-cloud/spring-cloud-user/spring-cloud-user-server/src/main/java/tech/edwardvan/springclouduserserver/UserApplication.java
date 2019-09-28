package tech.edwardvan.springclouduserserver;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Idea启动参数:
 * --server.port=8081
 * --server.port=8082
 *
 * @author EdwardVan
 */
@SpringBootApplication
@MapperScan("tech.edwardvan.springclouduserserver.dao")
@EnableEurekaClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
