package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * 短信验证码配置
 *
 * @author EdwardVan
 */
@Data
public class SmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 有效时长(s)
     */
    private int expireIn = 60;

    /**
     * 需要验证的地址(,隔开)
     */
    private String urls;

}
