package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * 图形验证码属性
 *
 * @author EdwardVan
 */
@Data
public class ImageCodeProperties {

    /**
     * 宽度
     */
    private int width = 67;
    /**
     * 高度
     */
    private int height = 23;

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
