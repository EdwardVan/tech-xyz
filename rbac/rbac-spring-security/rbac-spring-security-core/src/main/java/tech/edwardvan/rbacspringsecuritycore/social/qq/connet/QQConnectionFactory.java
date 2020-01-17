package tech.edwardvan.rbacspringsecuritycore.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import tech.edwardvan.rbacspringsecuritycore.social.qq.api.QQApi;


/**
 * QQ Connection 工厂
 *
 * @author EdwardVan
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQApi> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQApiAdapter());
    }
}
