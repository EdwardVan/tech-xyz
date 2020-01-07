package tech.edwardvan.rbacspringsecuritycore.authentication.mobile;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 开启短信登录功能
 *
 * @author EdwardVan
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(SmsCodeAuthenticationSecurityConfig.class)
public @interface EnableSmsCodeAuthentication {
}
