package tech.edwardvan.rbacspringsecuritycore.validate.code;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;
import tech.edwardvan.rbacspringsecuritycore.validate.code.image.ImageValidateCodeGenerator;
import tech.edwardvan.rbacspringsecuritycore.validate.code.sms.DefaultSmsCodeSender;
import tech.edwardvan.rbacspringsecuritycore.validate.code.sms.SmsCodeSender;

/**
 * 验证码校验Security配置
 *
 * @author EdwardVan
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(SpringSecurityProperties.class)
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private Filter validateCodeFilter;

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

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

}
