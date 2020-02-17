package tech.edwardvan.rbacspringsecuritycore.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;

import javax.sql.DataSource;

/**
 * 第三方登录配置类
 *
 * @author EdwardVan
 */
@Configuration
@EnableSocial
@ComponentScan
public class SecuritySocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SpringSecurityProperties springSecurityProperties;

    /**
     * 数据库sql文件位置:{@link org.springframework.social.connect.jdbc}
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        //对应表前缀
        repository.setTablePrefix("social_");
        return repository;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Bean
    public SpringSocialConfigurer socialSecurityConfig() {
        SpringSocialConfigurer springSocialConfigurer = new SpringSocialConfigurer();
        //设置注册页面地址
        springSocialConfigurer.signupUrl(springSecurityProperties.getBrowser().getSignUpUrl());
        return springSocialConfigurer;
    }

    /**
     * 该工具类提供的功能:
     * 1. 在注册过程中拿到spring social的信息
     * 2. 用户注册完成后将业务系统的用户id传给spring social 并保存
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }
}
