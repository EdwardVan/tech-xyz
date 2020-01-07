package tech.edwardvan.rbacspringsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.edwardvan.rbacspringsecuritybrowser.EnableBrowserSecurity;
import tech.edwardvan.rbacspringsecuritycore.validate.code.EnableValidateCode;

/**
 * @author EdwardVan
 */
@SpringBootApplication
@EnableBrowserSecurity
public class RbacSpringSecurityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacSpringSecurityDemoApplication.class, args);
    }
}
