package tech.edwardvan.rbacspringsecuritybrowser;

import org.springframework.context.annotation.Import;
import tech.edwardvan.rbacspringsecuritybrowser.config.BrowserSecurityConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 开启浏览器端安全
 *
 * @author EdwardVan
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(BrowserSecurityConfig.class)
public @interface EnableBrowserSecurity {
}
