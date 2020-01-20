package tech.edwardvan.rbacspringsecuritybrowser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author EdwardVan
 */
@Component
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 通过用户名找不到用户信息异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, passwordEncoder.encode("123"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    /**
     * 根据用户id查找用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return new SocialUser(userId, passwordEncoder.encode("123"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
