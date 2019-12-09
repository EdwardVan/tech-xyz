/**
 * 
 */
package tech.edwardvan.rbacspringsecuritycore.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author EdwardVan
 */
@ConfigurationProperties(prefix = "rbac.spring.security")
@Data
public class SpringSecurityProperties {
	private BrowserProperties browser = new BrowserProperties();
}

