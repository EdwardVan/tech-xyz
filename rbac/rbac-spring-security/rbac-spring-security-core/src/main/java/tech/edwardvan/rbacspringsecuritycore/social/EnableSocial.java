package tech.edwardvan.rbacspringsecuritycore.social;

import org.springframework.context.annotation.Import;
import tech.edwardvan.rbacspringsecuritycore.authentication.mobile.SmsCodeAuthenticationSecurityConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 开启第三方登录功能
 *
 * @author EdwardVan
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(SmsCodeAuthenticationSecurityConfig.class)
public @interface EnableSocial {
}
