package tech.edwardvan.rbacspringsecuritybrowser.pojo;


import lombok.Data;

/**
 * Spring Social 第三方登录信息
 *
 * @author EdwardVan
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;
}
