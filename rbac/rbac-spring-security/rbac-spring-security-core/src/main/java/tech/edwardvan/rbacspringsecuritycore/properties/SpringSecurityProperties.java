package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统配置映射类
 *
 * @author EdwardVan
 */
@ConfigurationProperties(prefix = "rbac.spring.security")
@Data
public class SpringSecurityProperties {
    /**
     * 浏览器端配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 第三方登录配置
     */
    private SocialProperties social = new SocialProperties();
}

