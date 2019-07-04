package tech.edwardvan.webspringboot.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.edwardvan.webspringboot.model.User;

import java.util.Arrays;

/**
 * 缓存配置类
 *
 * @author EdwardVan
 */
@Configuration
public class CacheConfig {

    @Bean("testKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String key = "";
            Object param = params[0];
            if (param instanceof User) {
                key = ((User) param).getId().toString();
            } else if (param instanceof Integer) {
                key = param.toString();
            }
            return "user[" + key + "]";
        };
    }
}
