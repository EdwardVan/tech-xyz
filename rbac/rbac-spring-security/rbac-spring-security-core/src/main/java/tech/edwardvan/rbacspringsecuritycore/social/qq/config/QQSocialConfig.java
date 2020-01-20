package tech.edwardvan.rbacspringsecuritycore.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import tech.edwardvan.rbacspringsecuritycore.properties.QQProperties;
import tech.edwardvan.rbacspringsecuritycore.properties.SpringSecurityProperties;
import tech.edwardvan.rbacspringsecuritycore.social.qq.connet.QQConnectionFactory;

/**
 * @author EdwardVan
 */
@Configuration
@ConditionalOnProperty(prefix = "rbac.spring.security.social.qq", name = "app-id")
public class QQSocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private SpringSecurityProperties springSecurityProperties;


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = springSecurityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //这里必须返回null
        return null;
    }
}
