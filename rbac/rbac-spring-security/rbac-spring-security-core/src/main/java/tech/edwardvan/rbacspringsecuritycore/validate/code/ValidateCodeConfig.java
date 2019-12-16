package tech.edwardvan.rbacspringsecuritycore.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;
import tech.edwardvan.rbacspringsecuritycore.validate.code.image.ImageValidateCodeGenerator;

/**
 * 验证码配置类
 *
 * @author EdwardVan
 */
@Configuration
@ComponentScan("tech.edwardvan.rbacspringsecuritycore.validate.code")
@EnableConfigurationProperties(SpringSecurityProperties.class)
public class ValidateCodeConfig {

    @Autowired
    private SpringSecurityProperties springSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator codeGenerator = new ImageValidateCodeGenerator();
//        codeGenerator.setSpringSecurityProperties(springSecurityProperties);
        return codeGenerator;
    }
}
