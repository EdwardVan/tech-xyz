package tech.edwardvan.rbacspringsecuritycore.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码校验异常
 *
 * @author EdwardVan
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
