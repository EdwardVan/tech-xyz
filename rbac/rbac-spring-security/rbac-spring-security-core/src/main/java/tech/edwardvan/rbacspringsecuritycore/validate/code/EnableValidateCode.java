package tech.edwardvan.rbacspringsecuritycore.validate.code;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 开启验证码功能注解
 *
 * @author EdwardVan
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(ValidateCodeConfig.class)
public @interface EnableValidateCode {
}
