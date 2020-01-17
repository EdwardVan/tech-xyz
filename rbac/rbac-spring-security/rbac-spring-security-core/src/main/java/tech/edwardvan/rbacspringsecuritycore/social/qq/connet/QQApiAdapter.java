
package tech.edwardvan.rbacspringsecuritycore.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import tech.edwardvan.rbacspringsecuritycore.social.qq.api.QQApi;
import tech.edwardvan.rbacspringsecuritycore.social.qq.api.QQUserInfo;


/**
 * QQApi适配器
 *
 * @author EdwardVan
 */
public class QQApiAdapter implements ApiAdapter<QQApi> {


    @Override
    public boolean test(QQApi api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQApi api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQApi api) {
        return null;
    }

    @Override
    public void updateStatus(QQApi api, String message) {
    }
}
