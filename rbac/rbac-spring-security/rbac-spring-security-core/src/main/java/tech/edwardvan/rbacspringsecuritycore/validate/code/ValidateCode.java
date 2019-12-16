package tech.edwardvan.rbacspringsecuritycore.validate.code;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 验证码类型
 * <p>
 * BeanName命名规范:xxxValidateCode
 * 举例:imageValidateCode
 *
 * @author EdwardVan
 */
@Data
public class ValidateCode {

    /**
     * 验证码代码
     */
    private String code;

    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 验证码是否过期
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
