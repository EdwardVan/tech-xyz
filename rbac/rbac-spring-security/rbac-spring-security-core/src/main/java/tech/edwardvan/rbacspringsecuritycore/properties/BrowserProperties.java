package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * @author EdwardVan
 */
@Data
public class BrowserProperties {
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
}
