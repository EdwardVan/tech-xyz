package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * 第三方登录配置
 *
 * @author EdwardVan
 */
@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

}
