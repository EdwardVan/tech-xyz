package tech.edwardvan.rbacspringsecuritybrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;

/**
 * BrowserSecurity配置
 *
 * @author EdwardVan
 */
@Configuration
@ComponentScan("tech.edwardvan.rbacspringsecuritybrowser")
@EnableConfigurationProperties(SpringSecurityProperties.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSecurityProperties springSecurityProperties;

    /**
     * 密码加密解密工具
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //使用表单登录
                .formLogin()
                //自定义登录页面
                .loginPage("")
                //自定义登陆表单提交请求地址
                .loginProcessingUrl("")
                .and()
                //授权配置
                .authorizeRequests()
                //指定任何用户都可以访问的URL
                .antMatchers(springSecurityProperties.getBrowser().getLoginPage()).permitAll()
                //任何尚未匹配的URL只需要对用户进行身份验证
                .anyRequest().authenticated();
    }
}
