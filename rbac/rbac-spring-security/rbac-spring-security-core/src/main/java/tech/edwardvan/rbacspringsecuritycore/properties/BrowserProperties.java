package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * 浏览器端配置类
 *
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
    /**
     * 记住我时间设置(单位:秒)
     */
    private int rememberMeSeconds = 3600;
}
