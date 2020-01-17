package tech.edwardvan.rbacspringsecuritycore.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;
import tech.edwardvan.rbacspringsecuritycore.social.qq.api.QQApi;
import tech.edwardvan.rbacspringsecuritycore.social.qq.api.QQApiImpl;


/**
 * QQ服务提供商
 *
 * @author EdwardVan
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQApi> {

    private String appId;
	/**
	 * 导向认证服务器地址
	 */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	/**
	 * 申请令牌地址
	 */
    private static final String URL_GET_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	public QQServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_GET_ACCESS_TOKEN));
	}


	@Override
    public QQApi getApi(String accessToken) {
        return new QQApiImpl(accessToken, appId);
    }
}
