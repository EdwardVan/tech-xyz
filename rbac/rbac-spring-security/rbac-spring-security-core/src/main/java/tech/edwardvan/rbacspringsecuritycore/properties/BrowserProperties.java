package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * @author EdwardVan
 */
@Data
public class BrowserProperties {
    /**
     * 登录页配置
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    /**
     * 登录成功跳转配置
     */
    private LoginSuccessReturnType loginSuccessReturnType = LoginSuccessReturnType.JSON;
}
