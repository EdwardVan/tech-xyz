package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * QQ登录配置
 *
 * @author EdwardVan
 */
@Data
public class QQProperties {

    private String appId;

    private String appSecret;

    private String providerId = "qq";

}
