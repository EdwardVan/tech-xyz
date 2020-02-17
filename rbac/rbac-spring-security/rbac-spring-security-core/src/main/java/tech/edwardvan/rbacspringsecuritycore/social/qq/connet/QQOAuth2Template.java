package tech.edwardvan.rbacspringsecuritycore.social.qq.connet;

import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * @author EdwardVan
 */
@Slf4j
public class QQOAuth2Template extends OAuth2Template {


    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //当useParametersForClientAuthentication为true时,请求参数才会携带client_id和client_secret
        setUseParametersForClientAuthentication(true);
    }


    /**
     * 解决Access Token获取结果非json问题
     * <p>
     * QQ互联文档:如果成功返回,即可在返回包中获取到Access Token.如下:
     * access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        log.info("获取accessToke的响应:{}", responseStr);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = Long.parseLong(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");

        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }


    /**
     * 解决以下问题:
     * Could not extract response: no suitable HttpMessageConverter found for response type [interface java.util.Map] and content type [text/html]
     * 官方默认的RestTemplate中没有处理text/html的处理器
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
