package tech.edwardvan.rbacspringsecuritybrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import tech.edwardvan.rbacspringsecuritybrowser.handler.BrowserAuthenctiationFailureHandler;
import tech.edwardvan.rbacspringsecuritybrowser.handler.BrowserAuthenticationSuccessHandler;
import tech.edwardvan.rbacspringsecuritycore.properties.SecurityConstants;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;
import tech.edwardvan.rbacspringsecuritycore.validate.code.ValidateCodeFilter;

import javax.sql.DataSource;

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

    @Autowired
    private BrowserAuthenticationSuccessHandler browserAuthenticationSuccessHandler;

    @Autowired
    private BrowserAuthenctiationFailureHandler browserAuthenctiationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;
    /**
     * 密码加密解密工具
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我功能tokenRepository配置
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //配置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        //启动自动建表
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            //表单登录配置
            .formLogin()
                //自定义登录页面
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                //自定义登陆表单提交请求地址
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                //登录成功处理器
                .successHandler(browserAuthenticationSuccessHandler)
                //登录失败处理器
                .failureHandler(browserAuthenctiationFailureHandler)
                .and()
            //记住我配置
            .rememberMe()
                //配置tokenRepository
                .tokenRepository(persistentTokenRepository())
                //有效时长
                .tokenValiditySeconds(springSecurityProperties.getBrowser().getRememberMeSeconds())
                //配置userDetailsService
                .userDetailsService(userDetailsService)
                .and()
            //授权配置
            .authorizeRequests()
                //指定任何用户都可以访问的URL
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        springSecurityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*"
                ).permitAll()
                //任何尚未匹配的URL只需要对用户进行身份验证
                .anyRequest().authenticated()
            .and()
            //关闭csrf
            .csrf().disable();
    }
}
