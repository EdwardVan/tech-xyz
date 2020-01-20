package tech.edwardvan.rbacspringsecuritycore.social;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 开启第三方登录功能
 *
 * @author EdwardVan
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(SecuritySocialConfig.class)
public @interface EnableSecuritySocial {
}
