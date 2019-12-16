package tech.edwardvan.rbacspringsecuritycore.properties;

import lombok.Data;

/**
 * 验证码配置
 *
 * @author EdwardVan
 */
@Data
public class ValidateCodeProperties {
	/**
	 * 图形验证码
	 */
    private ImageCodeProperties image = new ImageCodeProperties();

}
