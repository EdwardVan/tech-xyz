package tech.edwardvan.rbacspringsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import tech.edwardvan.rbacspringsecuritydemo.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author EdwardVan
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {
        //将用户信息保存到业务库系统
        //相关代码省略

        //将用户信息与Spring Social第三方信息关联
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("me")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping(value = "/{userId}")
    public void getUser(@PathVariable(value = "userId") Integer id) {
        log.info(id.toString());
    }
}
