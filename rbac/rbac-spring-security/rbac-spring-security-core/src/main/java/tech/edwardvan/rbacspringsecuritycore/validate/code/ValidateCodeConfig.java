package tech.edwardvan.rbacspringsecuritycore.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;
import tech.edwardvan.rbacspringsecuritycore.validate.code.image.ImageValidateCodeGenerator;
import tech.edwardvan.rbacspringsecuritycore.validate.code.sms.DefaultSmsCodeSender;
import tech.edwardvan.rbacspringsecuritycore.validate.code.sms.SmsCodeSender;

/**
 * 验证码配置类
 *
 * @author EdwardVan
 */
@Configuration
@ComponentScan("tech.edwardvan.rbacspringsecuritycore.validate.code")
@EnableConfigurationProperties(SpringSecurityProperties.class)
public class ValidateCodeConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        return new ImageValidateCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
